package iut.gon.othello.model.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.hexagonalcoordinates.CoordinateDoubled;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

public class FactoryDoubled implements IFactory{

	@Override
    public IState testState() {
		IState empty = this.emptyState();
        Map<Coordinate, Token> board = new HashMap<>(empty.board());
        
        board.put(new CoordinateDoubled(5, 11), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(5, 13), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(4, 2), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(4, 8), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(3, 9), new Ring(Team.BLACK));
        
        board.put(new CoordinateDoubled(5, 15), new Ring(Team.WHITE));
        board.put(new CoordinateDoubled(5, 17), new Ring(Team.WHITE));
        board.put(new CoordinateDoubled(6, 12), new Ring(Team.WHITE));
        board.put(new CoordinateDoubled(3, 13), new Ring(Team.WHITE));
        board.put(new CoordinateDoubled(3, 15), new Ring(Team.WHITE));
        
        board.put(new CoordinateDoubled(4, 6), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(1, 11), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(9, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateDoubled(6, 2), new Pawn(Team.BLACK));

        board.put(new CoordinateDoubled(6, 8), new Pawn(Team.WHITE));
        board.put(new CoordinateDoubled(1, 15), new Pawn(Team.WHITE));
        board.put(new CoordinateDoubled(3, 9), new Pawn(Team.WHITE));
        board.put(new CoordinateDoubled(10, 10), new Pawn(Team.WHITE));
        
        return new State(board, Team.BLACK, new ArrayList<>());
    }

	@Override
	public IState stateForBlackLineTest() {
		IState empty = this.emptyState();
		Map<Coordinate, Token> board = new HashMap<>(empty.board());
		
		board.put(new CoordinateDoubled(5, 11), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(5, 13), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(4, 2), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(8, 8), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(9, 9), new Ring(Team.BLACK));
		
		board.put(new CoordinateDoubled(3, 13), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(3, 15), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(5, 15), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(5, 17), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(6, 12), new Ring(Team.WHITE));
		
		board.put(new CoordinateDoubled(4, 8), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(2, 6), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(1, 5), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(4, 6), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(2, 10), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(1, 9), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(0, 8), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(4, 14), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(4, 16), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(4, 18), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 16), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 6), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 4), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 0), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(7, 1), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(8, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(9, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(7, 11), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(8, 12), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(8, 10), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(8, 14), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(8, 16), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(10, 12), new Pawn(Team.BLACK));
		
		board.put(new CoordinateDoubled(1, 15), new Pawn(Team.WHITE));
		board.put(new CoordinateDoubled(3, 9), new Pawn(Team.WHITE));
		board.put(new CoordinateDoubled(8, 6), new Pawn(Team.WHITE));
		board.put(new CoordinateDoubled(10, 10), new Pawn(Team.WHITE));
		
		return new State(board, Team.BLACK, new ArrayList<>());
	}

	@Override
	public IState stateForWhiteLineTest() {
		IState empty = this.emptyState();
		Map<Coordinate, Token> board = new HashMap<>(empty.board());
		
		board.put(new CoordinateDoubled(5, 11), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(5, 13), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(4, 2), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(8, 8), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(9, 9), new Ring(Team.BLACK));
		
		board.put(new CoordinateDoubled(3, 13), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(3, 15), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(5, 15), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(5, 17), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(6, 12), new Ring(Team.WHITE));
		
		board.put(new CoordinateDoubled(5, 9), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 8), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(4, 8), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(2, 6), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(1, 5), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(4, 6), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(2, 10), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(1, 9), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(0, 8), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(4, 14), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(4, 16), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(4, 18), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 16), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 6), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 4), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(6, 0), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(7, 1), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(8, 2), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(9, 3), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(7, 11), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(8, 12), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(8, 10), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(8, 14), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(8, 16), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(10, 12), new Pawn(Team.BLACK));
		
		board.put(new CoordinateDoubled(1, 15), new Pawn(Team.WHITE));
		board.put(new CoordinateDoubled(3, 9), new Pawn(Team.WHITE));
		board.put(new CoordinateDoubled(8, 6), new Pawn(Team.WHITE));
		board.put(new CoordinateDoubled(10, 10), new Pawn(Team.WHITE));
		
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
		
		board.put(new CoordinateDoubled(5, 11), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(5, 13), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(4, 2), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(8, 8), new Ring(Team.BLACK));
		board.put(new CoordinateDoubled(9, 9), new Ring(Team.BLACK));
		
		board.put(new CoordinateDoubled(2, 12), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(2, 16), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(5, 15), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(5, 17), new Ring(Team.WHITE));
		board.put(new CoordinateDoubled(6, 12), new Ring(Team.WHITE));
		
		board.put(new CoordinateDoubled(4, 10), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(3, 13), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
		board.put(new CoordinateDoubled(3, 5), new Pawn(Team.BLACK));
		
		board.put(new CoordinateDoubled(4, 8), new Pawn(Team.WHITE));
		board.put(new CoordinateDoubled(4, 6), new Pawn(Team.WHITE));
		board.put(new CoordinateDoubled(3, 9), new Pawn(Team.WHITE));
		board.put(new CoordinateDoubled(4, 12), new Pawn(Team.WHITE));
		board.put(new CoordinateDoubled(4, 14), new Pawn(Team.WHITE));
		
		return new State(board, Team.BLACK, new ArrayList<>());
	}
}