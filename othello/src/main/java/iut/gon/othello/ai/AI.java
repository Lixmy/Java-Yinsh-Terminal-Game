package iut.gon.othello.ai;

import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;

/**
 * Interface définissant le contrat pour une Intelligence Artificielle (IA).
 * Toute classe implémentant cette interface doit fournir la logique nécessaire 
 * pour analyser un état de jeu et déterminer la prochaine action à jouer.
 */
public interface AI{

    /**
     * Détermine et retourne la meilleure action à effectuer selon l'algorithme de l'IA.
     *
     * @param state L'état actuel du jeu à partir duquel l'IA doit prendre sa décision.
     * @return L'{@link Action} choisie par l'IA pour le tour courant.
     */
    Action chooseMove(IState state);
}