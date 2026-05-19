package iut.gon.othello.model.state;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class State implements IState {

	private Map<Coordinate, Token> board;
	private Team turn;
	private List<Set<Coordinate>> lines;
	
	
	
	public State(Map<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) {
		this.board = board;
		this.turn = turn;
		this.lines = lines;
	}

	public boolean isInField(Coordinate c) {
		return false;
	}
	
	public Team winner() {
		
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof State s) {
			return false;
		}
		else return this.turn = turn, this.lines = lines, this.board = board;
	}

}
