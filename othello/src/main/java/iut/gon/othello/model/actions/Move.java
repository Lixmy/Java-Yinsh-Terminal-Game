package iut.gon.othello.model.actions;

import java.util.Objects;

import iut.gon.hexagonalcoordinates.Coordinate;

/**
 * Représente une action de déplacement sur le plateau de jeu. Cette classe
 * encapsule les informations nécessaires pour déplacer un élément (comme un
 * anneau) depuis une coordonnée de départ vers une coordonnée d'arrivée.
 */
public class Move extends Action {
	private Coordinate from;
	private Coordinate to;

	/**
	 * Construit une nouvelle action de déplacement.
	 *
	 * @param from La coordonnée de départ du déplacement.
	 * @param to   La coordonnée de destination du déplacement.
	 */
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