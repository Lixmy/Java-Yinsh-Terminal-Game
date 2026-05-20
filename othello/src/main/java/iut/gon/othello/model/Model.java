package iut.gon.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Token;

public class Model {
	private IState currentState;

	public Model(IState currentState) {
		super();
		this.currentState = currentState;
	}

	public IState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(IState currentState) {
		this.currentState = currentState;
	}

	public Set<Coordinate> movesFrom(Coordinate from) {
		return currentState.availableMoves(from);
	}
	
	public void moveRing(Coordinate from, Coordinate to) {
		Move m = new Move(from, to);
		currentState = currentState.move(m);
	}
	
	public List<Set<Coordinate>> getPawnsLines() {
		return currentState.lines();
	}
	
	public void removeLine(Set<Coordinate> line, Coordinate ring) {
		RemoveLine r = new RemoveLine(line, ring);
		currentState = currentState.removeLine(r);
	}
	
	public Map<Coordinate, Token> getBoard() {
		return currentState.board();
	}
	
	public Token getTokenAt(Coordinate c) {
		return currentState.board().get(c);
	}
	
	public boolean isInField(Coordinate c) {
		return currentState.board().containsKey(c);
	}
	
	public List<Coordinate> getRings(Team team) {
		return currentState.rings().get(team);
	}
	
	public List<Coordinate> getPawn(Team team) {
		List<Coordinate> result = new ArrayList<>();
	    for (Map.Entry<Coordinate, Token> entry : currentState.board().entrySet()) {
	        Token t = entry.getValue();
	        if (t instanceof Pawn && t.getTeam() == team) {
	            result.add(entry.getKey());
	        }
	    }
	    return result;
	}
	
	public Team getTurn() {
		return currentState.turn();
	}
	
	public void removeToken(Coordinate c) {
		currentState = currentState.removeToken(c);
	}
	
	public void toggleToken(Coordinate position, Team team, Class<?> token) {
		currentState = currentState.toggleToken(position, team, token);
	}
	
}
