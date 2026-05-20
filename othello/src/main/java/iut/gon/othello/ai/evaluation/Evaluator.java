package iut.gon.othello.ai.evaluation;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.state.IState;

/**
 * Classe responsable de l'évaluation heuristique d'un état de jeu pour l'intelligence artificielle.
 * Elle calcule un score numérique représentant l'avantage ou le désavantage de l'état courant
 * du point de vue de l'équipe contrôlée par l'IA.
 */
public class Evaluator {

    /**
     * L'équipe contrôlée par l'intelligence artificielle.
     */
    private final Team aiTeam;

    public Evaluator(Team aiTeam) {
        this.aiTeam = aiTeam;
    }

    /**
     * Évalue un état de jeu et retourne un score heuristique.
     * Un score très élevé (100000) indique une victoire de l'IA, tandis qu'un score très bas (-100000)
     * indique une défaite. Le score intermédiaire favorise les états où l'IA a moins d'anneaux 
     * sur le plateau que l'adversaire (ce qui signifie généralement qu'elle a marqué des points) 
     * et où sa mobilité (nombre de coups possibles) est supérieure à celle de l'adversaire.
     *
     * @param state L'état du jeu à évaluer.
     * @return Le score calculé. Plus le score est élevé, plus l'état est favorable à l'IA.
     */
    public int evaluate(IState state) {

        if (state.winner() == aiTeam) {
            return 100000;
        }

        if (state.winner() == aiTeam.other()) {
            return -100000;
        }

        int score = 0;

        int myRings = state.rings().get(aiTeam).size();
        int enemyRings = state.rings().get(aiTeam.other()).size();

        score += (enemyRings - myRings) * 1000;

        int myMoves = countMoves(state, aiTeam);
        int enemyMoves = countMoves(state, aiTeam.other());

        score += (myMoves - enemyMoves) * 2;

        return score;
    }

    /**
     * Calcule le nombre total de mouvements disponibles pour une équipe donnée
     * en additionnant les mouvements possibles pour chacun de ses anneaux sur le plateau.
     *
     * @param state L'état actuel du jeu.
     * @param team  L'équipe pour laquelle il faut compter les mouvements.
     * @return Le nombre total de mouvements possibles pour l'équipe spécifiée.
     */
    private int countMoves(IState state, Team team) {

        int total = 0;

        for (Coordinate ring : state.rings().get(team)) {
            total += state.availableMoves(ring).size();
        }

        return total;
    }
}