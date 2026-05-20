package iut.gon.othello.ai.evaluation;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.state.IState;

public class Evaluator {

    private final Team aiTeam;

    public Evaluator(Team aiTeam) {
        this.aiTeam = aiTeam;
    }

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

    private int countMoves(IState state, Team team) {

        int total = 0;

        for (Coordinate ring : state.rings().get(team)) {
            total += state.availableMoves(ring).size();
        }

        return total;
    }
}