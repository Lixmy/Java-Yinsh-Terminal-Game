package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

/**
 * Classe abstraite de base représentant un jeton (pièce de jeu) sur le plateau.
 * Cette classe sert de fondation pour tous les éléments physiques posés sur les cases, 
 * tels que les pions (Pawn) ou les anneaux (Ring).
 */
public abstract class Token {
	protected Team team;
	
	/**
	 * Construit un nouveau jeton en l'associant à une équipe spécifique.
	 *
	 * @param team L'équipe ({@link Team}) propriétaire de ce jeton initialement.
	 */
	public Token(Team team) {
		this.team = team;
	}
	
	/**
	 * Récupère l'équipe à laquelle appartient actuellement ce jeton.
	 *
	 * @return L'équipe ({@link Team}) propriétaire.
	 */
	public Team getTeam() {
		return this.team;
	}
	
	/**
	 * Modifie l'équipe propriétaire de ce jeton.
	 *
	 * @param team La nouvelle équipe ({@link Team}) à assigner à ce jeton.
	 */
	public void setTeam(Team team) {
		this.team = team;
	}
	
	/**
	 * Retourne une représentation textuelle spécifique au type de jeton.
	 *
	 * @return Une chaîne de caractères identifiant le type de jeton (ex: "Pawn" ou "Ring").
	 */
	public abstract String charRepr();
	
	/**
	 * Crée et retourne une copie (clone) exacte de ce jeton.
	 *
	 * @return Une nouvelle instance de {@link Token} de même type et appartenant à la même équipe.
	 */
	public abstract Token clone();
}