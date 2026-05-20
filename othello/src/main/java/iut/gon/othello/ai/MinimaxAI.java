package iut.gon.othello.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.othello.ai.evaluation.Evaluator;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

public class MinimaxAI implements AI {

    
    private final int maxDepth;
    private final Evaluator evaluator;

    public MinimaxAI(Team aiTeam, int maxDepth) {
        
        this.maxDepth = maxDepth;
        this.evaluator = new Evaluator(aiTeam);
    }

    @Override
    public Action chooseMove(IState state) {
        List<Action> actions = getPossibleActions(state);

        Action bestAction = null;
        int bestScore = Integer.MIN_VALUE;

        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (Action action : actions) {
            IState nextState = applyAction(state, action);

            int score = minimax(nextState, maxDepth - 1, false, alpha, beta);

            if (score > bestScore) {
                bestScore = score;
                bestAction = action;
            }

            alpha = Math.max(alpha, bestScore);
        }

        return bestAction;
    }

    private int minimax(IState state, int depth, boolean maximizingPlayer, int alpha, int beta) {
        if (depth == 0 || state.winner() != null) {
            return evaluate(state);
        }

        List<Action> actions = getPossibleActions(state);

        if (actions.isEmpty()) {
            return evaluate(state);
        }

        if (maximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;

            for (Action action : actions) {
                IState nextState = applyAction(state, action);

                int score = minimax(nextState, depth - 1, false, alpha, beta);

                bestScore = Math.max(bestScore, score);
                alpha = Math.max(alpha, bestScore);

                if (beta <= alpha) {
                    break;
                }
            }

            return bestScore;
        }

        int bestScore = Integer.MAX_VALUE;

        for (Action action : actions) {
            IState nextState = applyAction(state, action);

            int score = minimax(nextState, depth - 1, true, alpha, beta);

            bestScore = Math.min(bestScore, score);
            beta = Math.min(beta, bestScore);

            if (beta <= alpha) {
                break;
            }
        }

        return bestScore;
    }

    private int evaluate(IState state) {
        return evaluator.evaluate(state);
    }

    private List<Action> getPossibleActions(IState state) {
        List<Action> actions = new ArrayList<>();

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

        for (Coordinate ringCoord : state.rings().get(state.turn())) {
            Set<Coordinate> possibleMoves = state.availableMoves(ringCoord);

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