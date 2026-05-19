package iut.gon.othello.model.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.othello.model.Team;

public record State (Map<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) implements IState {
	
	public IState move(Move move) {
		Map<Coordinate, Token> newBoard = new HashMap<>(board);
        return new State(newBoard, turn, lines);
	}

	public boolean isInField(Coordinate c) {
		return false;
	}
	
	public Team winner() {
		return turn;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		return Objects.equals(board, other.board) && Objects.equals(lines, other.lines) && turn == other.turn;
	}

}
