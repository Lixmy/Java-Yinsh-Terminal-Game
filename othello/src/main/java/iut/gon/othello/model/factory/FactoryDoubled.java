package iut.gon.othello.model.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.hexagonalcoordinates.CoordinateDoubled;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Token;

public class FactoryDoubled implements IFactory{

	@Override
    public IState testState() {
		/*
        IState empty = this.emptyState();
        Map<Coordinate, Token> board = new HashMap<>(empty.board());
        
        board.put(new CoordinateDoubled(-3, -1, 4), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(1, 0, -1), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(2, 0, -2), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(-2, 3, -1), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(-2, 4, -2), new Ring(Team.BLACK));
        
        board.put(new CoordinateDoubled(3, 0, -3), new Ring(Team.WHITE));
        board.put(new CoordinateDoubled(4, 0, -4), new Ring(Team.WHITE));
        board.put(new CoordinateDoubled(3, -2, 1), new Ring(Team.WHITE));
        board.put(new CoordinateDoubled(4, -2, 2), new Ring(Team.WHITE));

        board.put(new CoordinateDoubled(0, -2, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(2, -2, 0), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(-1, -1, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(-4, 1, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(3, -4, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(-5, 4, 1), new Pawn(Team.BLACK));
        
        board.put(new CoordinateDoubled(1, -2, 1), new Pawn(Team.WHITE));
        board.put(new CoordinateDoubled(5, -4, 1), new Pawn(Team.WHITE));
        board.put(new CoordinateDoubled(-3, 3, 0), new Pawn(Team.WHITE));
        board.put(new CoordinateDoubled(-2, 5, -3), new Pawn(Team.WHITE));
        
        return new State(board, Team.BLACK, new ArrayList<>());
        */
		
		return null;
    }

	@Override
	public IState stateForBlackLineTest() {
		IState empty = this.emptyState();
		Map<Coordinate, Token> board = new HashMap<>(empty.board());
		
		// TODO : Placements...
		
		return new State(board, Team.BLACK, new ArrayList<>());
	}

	@Override
	public IState stateForWhiteLineTest() {
		IState empty = this.emptyState();
		Map<Coordinate, Token> board = new HashMap<>(empty.board());
		
		// TODO : Placements...
		
		return new State(board, Team.WHITE, new ArrayList<>());
	}

	@Override
    public IState emptyState() {
        Map<Coordinate, Token> board = new HashMap<>();
        
        int radius = 5;
        for (int q = -radius; q <= radius; q++) {
            for (int r = Math.max(-radius, -q - radius); r <= Math.min(radius, -q + radius); r++) {
                
                int col = 2 * q + r + 9;
                int row = r + 5;
                
                board.put(new CoordinateDoubled(row, col), null);
            }
        }
        
        return new State(board, Team.BLACK, new ArrayList<>());
    }

	@Override
	public IState doubleLineStateTest() {
		IState empty = this.emptyState();
		Map<Coordinate, Token> board = new HashMap<>(empty.board());
		
		// TODO : Placements...
		
		return new State(board, Team.BLACK, new ArrayList<>());
	}
	
}
