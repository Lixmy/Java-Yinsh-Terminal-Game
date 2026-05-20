package iut.gon.othello.model.factory;

import iut.gon.othello.model.state.IState;

/**
 * Interface définissant le contrat pour les fabriques d'états de jeu.
 * Elle déclare les méthodes nécessaires pour générer différentes configurations 
 * de plateau, qu'elles soient vides ou préremplies pour des scénarios de test.
 */
public interface IFactory {
    /**
     * Génère un état de jeu de test standard avec une disposition prédéfinie.
     *
     * @return Un {@link IState} configuré pour des tests généraux.
     */
    IState testState();
    /**
     * Génère un état de jeu configuré pour tester la formation et la suppression 
     * de lignes pour l'équipe noire.
     *
     * @return Un {@link IState} orienté pour les tests de lignes noires.
     */
    IState stateForBlackLineTest();
    /**
     * Génère un état de jeu configuré pour tester la formation et la suppression 
     * de lignes pour l'équipe blanche.
     *
     * @return Un {@link IState} orienté pour les tests de lignes blanches.
     */
    IState stateForWhiteLineTest();
    /**
     * Génère un état de jeu représentant un plateau initialement vide.
     *
     * @return Un {@link IState} vide (aucun pion ni anneau placé).
     */
    IState emptyState();
    /**
     * Génère un état de jeu configuré pour tester la gestion des lignes doubles 
     * ou multiples formées simultanément.
     *
     * @return Un {@link IState} orienté pour les tests de lignes multiples.
     */
    IState doubleLineStateTest();
}