package iut.gon.othello.model.actions;

import iut.gon.hexagonalcoordinates.Coordinate;

public class Move extends Action {
	private Coordinate from;
	private Coordinate to;
	
	public Move(Coordinate from, Coordinate to) {
		this.from = from;
		this.to = to;
	}
	
	public void setFrom(Coordinate from) {
		this.from = from;
	}
	public void setTo(Coordinate to) {
		this.to = to;
	}
	
	public Coordinate getFrom() {
		return this.from;
	}
	public Coordinate getTo() {
		return this.to;
	}
}
