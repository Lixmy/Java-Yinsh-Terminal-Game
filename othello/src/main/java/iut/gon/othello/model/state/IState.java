package iut.gon.othello.model.state;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IState {
	
	IState move(Move move);
	
	IState removeLine(RemoveLine rm);
	
	Set<Coordinate> availableMoves(Coordinate from);
	
	Map<Coordinate, Token> board();
	
	Map<Team, List<Coordinate>> rings();
	
	List<Set<Coordinate>> lines();
	
	Team turn();
	
	List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> board);
	
}
