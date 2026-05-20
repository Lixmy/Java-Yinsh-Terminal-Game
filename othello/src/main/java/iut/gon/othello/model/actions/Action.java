package iut.gon.othello.model.actions;

/**
 * Classe abstraite de base représentant une action de jeu générique.
 * Toutes les actions spécifiques pouvant être effectuées sur le plateau de jeu 
 * (comme un déplacement de type {@link Move} ou le retrait d'une ligne de type {@link RemoveLine}) 
 * doivent hériter de cette classe.
 */
public abstract class Action {}