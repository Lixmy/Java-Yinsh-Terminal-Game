package iut.gon.othello.model;

import java.awt.Color;

public enum Team {
	BLACK(Color.BLACK),
	WHITE(Color.WHITE);
	
	private Color color;
	
	Team(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}
	
	public Team other() {
		if (this.equals(BLACK)) {
			return WHITE;
		}
		return BLACK;
	}
}