package iut.gon.othello.model.actions;

import java.util.Objects;

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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		return Objects.equals(from, other.from) && Objects.equals(to, other.to);
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}
}
