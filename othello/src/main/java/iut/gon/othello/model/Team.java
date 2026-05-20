package iut.gon.othello.model;

import java.awt.Color;

/**
 * Énumération représentant les deux équipes (ou joueurs) de la partie.
 * Chaque équipe est associée à une couleur spécifique (java.awt.Color) 
 * pour faciliter son affichage dans une interface graphique.
 */
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
	
	/**
	 * Détermine et retourne l'équipe adverse.
	 *
	 * @return {@link Team#WHITE} si l'équipe actuelle est {@link Team#BLACK}, et inversement.
	 */
	public Team other() {
		if (this.equals(BLACK)) {
			return WHITE;
		}
		return BLACK;
	}
}