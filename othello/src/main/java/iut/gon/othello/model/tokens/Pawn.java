package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

/**
 * Représente un pion (Pawn) sur le plateau de jeu.
 * Dans cette variante du jeu, un pion a la particularité de pouvoir 
 * être retourné (changer d'équipe) lorsqu'un anneau saute par-dessus lui.
 */
public class Pawn extends Token {

	/**
	 * Construit un nouveau pion pour une équipe donnée.
	 *
	 * @param team L'équipe ({@link Team}) à laquelle appartient ce pion initialement.
	 */
	public Pawn(Team team) {
		super(team);
	}

	/**
	 * Retourne une représentation textuelle de ce type de jeton.
	 *
	 * @return La chaîne de caractères "Pawn".
	 */
	@Override
	public String charRepr() {
		return "Pawn";
	}

	/**
	 * Crée et retourne une copie (clone) exacte de ce pion, 
	 * appartenant à la même équipe.
	 *
	 * @return Une nouvelle instance de {@link Pawn} configurée avec la même équipe.
	 */
	@Override
	public Token clone() {
		return new Pawn(this.getTeam());
	}
	
	/**
	 * Inverse l'équipe propriétaire de ce pion.
	 * Cette méthode est appelée lorsqu'un anneau effectue un saut par-dessus 
	 * le pion, le forçant à se retourner pour rejoindre l'équipe adverse.
	 */
	public void changeTeam() {
		this.setTeam(this.getTeam().other());
	}
}