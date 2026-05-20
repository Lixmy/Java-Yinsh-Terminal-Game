package iut.gon.othello.model.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.hexagonalcoordinates.CoordinateCube;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

public class FactoryCube implements IFactory {

	@Override
    public IState testState() {
        IState empty = this.emptyState();
        Map<Coordinate, Token> board = new HashMap<>(empty.board());
        
        board.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
        board.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
        board.put(new CoordinateCube(2, 0, -2), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
        
        board.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
        board.put(new CoordinateCube(4, 0, -4), new Ring(Team.WHITE));
        board.put(new CoordinateCube(3, -2, -1), new Ring(Team.WHITE));
        board.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
        board.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));

        board.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-4, 1, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(3, -4, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-5, 4, 1), new Pawn(Team.BLACK));
        
        board.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(-3, 3, 0), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));
        
        return new State(board, Team.BLACK, new ArrayList<>());
    }

	@Override
	public IState stateForBlackLineTest() {
		IState empty = this.emptyState();
		Map<Coordinate, Token> board = new HashMap<>(empty.board());
		
		board.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
		board.put(new CoordinateCube(2, 0, -2), new Ring(Team.BLACK));
		board.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
		board.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
		board.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
		
		board.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
		board.put(new CoordinateCube(4, 0, -4), new Ring(Team.WHITE));
		board.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
		board.put(new CoordinateCube(3,-2, -1), new Ring(Team.WHITE));
		board.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
		
		board.put(new CoordinateCube(0, -1, 1), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, -3, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, -4, 4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, -3, 1), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, -4, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, -5, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(3, -4, 1), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(3, -1, -2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(4, -1, -3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(5, -1, -4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(3, 1, -4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, 2, -2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, 3, -3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-1, 3, -2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(1, 3, -4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, 3, -5), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-1, 5, -4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-2, 1, 1), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-3, 1, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-4, 1, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-5, 1, 4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-5, 2, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-5, 3, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-5, 4, 1), new Pawn(Team.BLACK));
		
		board.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
		board.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
		board.put(new CoordinateCube(-3, 3, 0), new Pawn(Team.WHITE));
		board.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));
		
		return new State(board, Team.BLACK, new ArrayList<>());
	}

	@Override
    public IState stateForWhiteLineTest() {
        IState empty = this.emptyState();
        Map<Coordinate, Token> board = new HashMap<>(empty.board());
        
        board.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
		board.put(new CoordinateCube(2, 0, -2), new Ring(Team.BLACK));
		board.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
		board.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
		board.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
		
		board.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
		board.put(new CoordinateCube(4, 0, -4), new Ring(Team.WHITE));
		board.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
		board.put(new CoordinateCube(3,-2, -1), new Ring(Team.WHITE));
		board.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
		
		board.put(new CoordinateCube(0, 0, 0), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-1, 1, 0), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, -1, 1), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, -3, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, -4, 4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, -3, 1), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, -4, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, -5, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(3, -4, 1), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(3, -1, -2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(4, -1, -3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(5, -1, -4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(3, 1, -4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, 2, -2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(0, 3, -3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-1, 3, -2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(1, 3, -4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, 3, -5), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-1, 5, -4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-2, 1, 1), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-3, 1, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-4, 1, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-5, 1, 4), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-5, 2, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-5, 3, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-5, 4, 1), new Pawn(Team.BLACK));
		
		board.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
		board.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
		board.put(new CoordinateCube(-3, 3, 0), new Pawn(Team.WHITE));
		board.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));
        
        return new State(board, Team.WHITE, IState.getPawnsLines(board));
    }

	@Override
	public IState emptyState() {
	    Map<Coordinate, Token> board = new HashMap<>();

	    int radius = 5;
	    for (int q = -radius; q <= radius; q++) {
	        for (int r = Math.max(-radius, -q - radius); r <= Math.min(radius, -q + radius); r++) {
	            int s = -q - r;
	            int absCount = 0;
	            if (Math.abs(q) == radius) absCount++;
	            if (Math.abs(r) == radius) absCount++;
	            if (Math.abs(s) == radius) absCount++;
	            if (absCount >= 2) continue;

	            board.put(new CoordinateCube(q, r, s), null);
	        }
	    }

	    return new State(board, Team.BLACK, new ArrayList<>());
	}

	@Override
    public IState doubleLineStateTest() {
        IState empty = this.emptyState();
        Map<Coordinate, Token> board = new HashMap<>(empty.board());
        
        board.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
		board.put(new CoordinateCube(2, 0, -2), new Ring(Team.BLACK));
		board.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
		board.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
		board.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
		
		board.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
		board.put(new CoordinateCube(4, 0, -4), new Ring(Team.WHITE));
		board.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
		board.put(new CoordinateCube(5, -3, -2), new Ring(Team.WHITE));
		board.put(new CoordinateCube(3, -3, 0), new Ring(Team.WHITE));
		
		board.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(-1, -2, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(1, -1, 0), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
		board.put(new CoordinateCube(3, -2, -1), new Pawn(Team.BLACK));
		
		board.put(new CoordinateCube(0, -1, 1), new Pawn(Team.WHITE));
		board.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.WHITE));
		board.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
		board.put(new CoordinateCube(2, -1, -1), new Pawn(Team.WHITE));
		board.put(new CoordinateCube(3, -1, -2), new Pawn(Team.WHITE));
        
        return new State(board, Team.BLACK, new ArrayList<>());
    }
	
}
