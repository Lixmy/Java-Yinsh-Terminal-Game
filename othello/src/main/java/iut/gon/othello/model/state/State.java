package iut.gon.othello.model.state;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.hexagonalcoordinates.Point;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

public record State (Map<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) implements IState {
	
	@Override
	public IState move(Move move) {
		Coordinate from = move.getFrom();
		Coordinate to = move.getTo();

		if (!this.isInField(from) || !this.isInField(to)) {
			throw new IndexOutOfBoundsException("Une coordonnée est extérieure au terrain.");
		}
		if (this.lines != null && !this.lines.isEmpty()) {
			throw new RuntimeException("L'état courant possède déjà une ligne à supprimer.");
		}
		
		Token tokenAtFrom = this.board.get(from);
		if (!(tokenAtFrom instanceof Ring) || tokenAtFrom.getTeam() != this.turn) {
			throw new IllegalArgumentException("La case de départ ne contient pas un de vos anneaux.");
		}
		if (!this.availableMoves(from).contains(to)) {
			throw new IllegalArgumentException("La case d'arrivée n'est pas atteignable.");
		}

		Map<Coordinate, Token> nextBoard = new HashMap<>(this.board);

		nextBoard.remove(from);
		nextBoard.put(from, new Pawn(this.turn));
		nextBoard.put(to, tokenAtFrom);

		Map<Point, Coordinate> pointToCoord = new HashMap<>();
		for (Coordinate c : this.board.keySet()) {
			pointToCoord.put(c.to2DCoordinate(), c);
		}
		
		Point pFrom = from.to2DCoordinate();
		Point pTo = to.to2DCoordinate();
		
		int diffX = pTo.x() - pFrom.x();
		int diffY = pTo.y() - pFrom.y();
		
		int distance = (diffY != 0) ? Math.abs(diffY) : Math.abs(diffX) / 2;
		
		int stepX = diffX / distance;
		int stepY = diffY / distance;
		
		for (int k = 1; k < distance; k++) {
			Point pathPoint = 
				new Point(pFrom.x() + k * stepX, pFrom.y() + k * stepY);
			
			Coordinate pathCoord = pointToCoord.get(pathPoint);
			Token tokenInPath = nextBoard.get(pathCoord);
			
			if (tokenInPath instanceof Pawn) {
				Pawn flippedPawn = (Pawn) tokenInPath.clone();
				flippedPawn.changeTeam();
				nextBoard.put(pathCoord, flippedPawn);
			}
		}
		
		List<Set<Coordinate>> nextLines = IState.getPawnsLines(nextBoard);

		Team nextTurn = this.turn;
		
		if (nextLines.isEmpty()) {
			nextTurn = this.turn.other();
		} else {
			nextLines.sort((line1, line2) -> {
				Team team1 = nextBoard.get(line1.iterator().next()).getTeam();
				Team team2 = nextBoard.get(line2.iterator().next()).getTeam();
				
				if (team1 == this.turn && team2 != this.turn) return -1;
				if (team1 != this.turn && team2 == this.turn) return 1;
				return 0;
			});
		}

		return new State(nextBoard, nextTurn, nextLines);
	}
	
	@Override
	public IState removeLine(RemoveLine rm) {
		Set<Coordinate> lineToRemove = rm.getLine();
		Coordinate ringPos = rm.getRing();

		if (!this.isInField(ringPos)) {
			throw new IndexOutOfBoundsException("L'anneau à supprimer est hors du terrain.");
		}
		for (Coordinate c : lineToRemove) {
			if (!this.isInField(c)) {
				throw new IndexOutOfBoundsException("Une coordonnée de la ligne est hors du terrain.");
			}
		}

		if (this.lines == null || this.lines.isEmpty()) {
			throw new RuntimeException("Aucune ligne à supprimer dans l'état actuel.");
		}
		if (!this.lines.contains(lineToRemove)) {
			throw new IllegalArgumentException("La ligne spécifiée n'est pas une ligne valide pour ce tour.");
		}

		Token token = this.board.get(ringPos);
		if (!(token instanceof Ring) || token.getTeam() != this.turn) {
			throw new IllegalArgumentException("La case spécifiée ne contient pas un de vos anneaux.");
		}

		Map<Coordinate, Token> nextBoard = new HashMap<>(this.board);

		for (Coordinate c : lineToRemove) {
			nextBoard.put(c, null);
		}
		nextBoard.put(ringPos, null);

		List<Set<Coordinate>> nextLines = IState.getPawnsLines(nextBoard);

		Team nextTurn = this.turn;
		if (nextLines.isEmpty()) {
			nextTurn = this.turn.other();
		}

		return new State(nextBoard, nextTurn, nextLines);
	}
	
	@Override
	public Set<Coordinate> availableMoves(Coordinate from) {
		Set<Coordinate> moves = new HashSet<>();
		
		Token startToken = this.board.get(from);
		if (!(startToken instanceof Ring)) {
			return moves;
		}
		
		Map<Point, Coordinate> pointToCoord = new HashMap<>();
		for (Coordinate c : this.board.keySet()) {
			pointToCoord.put(c.to2DCoordinate(), c);
		}
		
		Point startPoint = from.to2DCoordinate();
		
		int[][] directions = {
			{-1, -1}, // NO
			{1, -1},  // NE
			{2, 0},   // E
			{1, 1},   // SE
			{-1, 1},  // SO
			{-2, 0}   // O
		};
		
		for (int[] dir : directions) {
			int dx = dir[0];
			int dy = dir[1];
			
			boolean isJumping = false;
			
			for (int k = 1; ; k++) {
				Point nextPoint = 
					new Point(startPoint.x() + k * dx, startPoint.y() + k * dy);
				
				// Si on sort des limites du plateau
				if (!pointToCoord.containsKey(nextPoint)) {
					break;
				}
				
				Coordinate nextCoord = pointToCoord.get(nextPoint);
				Token tokenAtNext = this.board.get(nextCoord);
				
				if (tokenAtNext == null) {
					moves.add(nextCoord);
					if (isJumping) {
						break;
					}
				} else if (tokenAtNext instanceof Ring) {
					break;
				} else if (tokenAtNext instanceof iut.gon.othello.model.tokens.Pawn) {
					isJumping = true;
				}
			}
		}
		
		return moves;
	}
	
	@Override
	public Map<Coordinate, Token> board() {
		return this.board;
	}
	
	@Override
	public Map<Team, List<Coordinate>> rings() {
		Map<Team, List<Coordinate>> allRings = new HashMap<>();
		
		for (Team t : Team.values()) {
			allRings.put(t, new java.util.ArrayList<>());
		}
		
		for (Map.Entry<Coordinate, Token> entry : this.board.entrySet()) {
			Coordinate currentCoord = entry.getKey();
			Token currentToken = entry.getValue();
			
			if (currentToken instanceof Ring) {
				Team ringTeam = currentToken.getTeam();
				allRings.get(ringTeam).add(currentCoord);
			}
		}
		
		return allRings;
	}
	
	@Override
	public List<Set<Coordinate>> lines() {
		return this.lines;
	}
	
	@Override
	public Team turn() {
		return this.turn;
	}
	
	@Override
	public Team winner() {
		Map<Team, List<Coordinate>> allRings = this.rings();
		for (Map.Entry<Team, List<Coordinate>> entry : allRings.entrySet()) {
			if (entry.getValue().size() <= 2) { 
				return entry.getKey();
			}
		}
		return null;
	}
	
	@Override
    public IState removeToken(Coordinate c) {
        if (!this.isInField(c)) {
            throw new IndexOutOfBoundsException("La coordonnée est hors du terrain.");
        }

        Map<Coordinate, Token> nextBoard = new HashMap<>(this.board);
        nextBoard.put(c, null);

        List<Set<Coordinate>> nextLines = IState.getPawnsLines(nextBoard);
        return new State(nextBoard, this.turn, nextLines);
    }
	
	@Override
    public IState toggleToken(Coordinate position, Team team, Class<?> tokenClass) {
        if (!this.isInField(position)) {
            throw new IndexOutOfBoundsException("La coordonnée est hors du terrain.");
        }

        Map<Coordinate, Token> nextBoard = new HashMap<>(this.board);
        Token currentToken = nextBoard.get(position);

        try {
            java.lang.reflect.Constructor<?> constructor = tokenClass.getConstructors()[0];
            Token newToken = (Token) constructor.newInstance(team);

            // Si la case contient déjà exactement le même type de pion/anneau de la même équipe, on l'enlève
            if (currentToken != null && currentToken.getClass() == tokenClass && currentToken.getTeam() == team) {
                nextBoard.remove(position);
            } 
            // Sinon, on remplace/place le nouveau jeton
            else {
                nextBoard.put(position, newToken);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création dynamique du Token", e);
        }

        List<Set<Coordinate>> nextLines = IState.getPawnsLines(nextBoard);
        return new State(nextBoard, this.turn, nextLines);
    }

	public boolean isInField(Coordinate c) {
		return this.board.containsKey(c);
	}
}