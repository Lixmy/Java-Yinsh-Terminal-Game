package iut.gon.othello.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

public class MinimaxAI implements AI {

    private final Team aiTeam;
    private final int maxDepth;

    public MinimaxAI(Team aiTeam, int maxDepth) {
        this.aiTeam = aiTeam;
        this.maxDepth = maxDepth;
    }

    @Override
    public Action chooseMove(IState state) {

        List<Action> actions = getPossibleActions(state);

        Action bestAction = null;
        int bestScore = Integer.MIN_VALUE;

        for (Action action : actions) {

            IState nextState = applyAction(state, action);

            int score = minimax(nextState, maxDepth - 1, false);

            if (score > bestScore) {
                bestScore = score;
                bestAction = action;
            }
        }

        return bestAction;
    }

    private int minimax(IState state, int depth, boolean maximizingPlayer) {

        if (depth == 0 || state.winner() != null) {
            return evaluate(state);
        }

        List<Action> actions = getPossibleActions(state);

        if (maximizingPlayer) {

            int bestScore = Integer.MIN_VALUE;

            for (Action action : actions) {

                IState nextState = applyAction(state, action);

                int score = minimax(nextState, depth - 1, false);

                bestScore = Math.max(bestScore, score);
            }

            return bestScore;

        } else {

            int bestScore = Integer.MAX_VALUE;

            for (Action action : actions) {

                IState nextState = applyAction(state, action);

                int score = minimax(nextState, depth - 1, true);

                bestScore = Math.min(bestScore, score);
            }

            return bestScore;
        }
    }

    private int evaluate(IState state) {

        if (state.winner() == aiTeam) {
            return 100000;
        }

        if (state.winner() == aiTeam.other()) {
            return -100000;
        }

        int score = 0;

        Map<Team, List<Coordinate>> rings = state.rings();

        int myRings = rings.get(aiTeam).size();
        int enemyRings = rings.get(aiTeam.other()).size();

        score += (myRings - enemyRings) * 1000;

        return score;
    }

    private List<Action> getPossibleActions(IState state) {

        List<Action> actions = new ArrayList<>();

        /*
         * Si une ligne doit être supprimée
         */
        if (state.lines() != null && !state.lines().isEmpty()) {

            for (Set<Coordinate> line : state.lines()) {

                for (Coordinate ringCoord : state.rings().get(state.turn())) {

                    Token token = state.board().get(ringCoord);

                    if (token instanceof Ring) {
                        actions.add(new RemoveLine(line, ringCoord));
                    }
                }
            }

            return actions;
        }

        /*
         * Sinon coups normaux
         */
        for (Coordinate ringCoord : state.rings().get(state.turn())) {

            Set<Coordinate> possibleMoves =
                    state.availableMoves(ringCoord);

            for (Coordinate destination : possibleMoves) {

                actions.add(new Move(ringCoord, destination));
            }
        }

        return actions;
    }

    private IState applyAction(IState state, Action action) {

        if (action instanceof Move move) {
            return state.move(move);
        }

        if (action instanceof RemoveLine removeLine) {
            return state.removeLine(removeLine);
        }

        throw new IllegalArgumentException("Action inconnue");
    }
}