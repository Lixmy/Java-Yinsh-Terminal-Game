package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

/**
 * Représente un anneau (Ring) sur le plateau de jeu.
 * Les anneaux sont les pièces mobiles principales du jeu : ils se déplacent, 
 * déposent des pions derrière eux et peuvent sauter par-dessus les pions existants.
 */
public class Ring extends Token {

	/**
	 * Construit un nouvel anneau pour une équipe donnée.
	 *
	 * @param team L'équipe ({@link Team}) à laquelle appartient cet anneau.
	 */
	public Ring(Team team) {
		super(team);
	}

	/**
	 * Retourne une représentation textuelle de ce type de jeton.
	 *
	 * @return La chaîne de caractères "Ring".
	 */
	@Override
	public String charRepr() {
		return "Ring";
	}

	/**
	 * Crée et retourne une copie (clone) exacte de cet anneau, 
	 * appartenant à la même équipe.
	 *
	 * @return Une nouvelle instance de {@link Ring} configurée avec la même équipe.
	 */
	@Override
	public Token clone() {
		return new Ring(this.getTeam());
	}
}