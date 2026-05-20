package iut.gon.othello.ai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.factory.FactoryCube;
import iut.gon.othello.model.state.IState;

public class MinimaxAITest {

    @Test
    void testChooseMoveReturnsAction() {
        IState state = new FactoryCube().testState();

        AI ai = new MinimaxAI(Team.BLACK, 2);

        Action action = ai.chooseMove(state);

        assertNotNull(action);
    }

    @Test
    void testChooseMoveReturnsMoveWhenPossible() {
        IState state = new FactoryCube().testState();

        AI ai = new MinimaxAI(Team.BLACK, 2);

        Action action = ai.chooseMove(state);

        assertTrue(action instanceof Move);
    }

    @Test
    void testChooseMoveReturnsNullIfNoMovePossible() {
        IState state = new FactoryCube().emptyState();

        AI ai = new MinimaxAI(Team.BLACK, 2);

        Action action = ai.chooseMove(state);

        assertNull(action);
    }

    @Test
    void testReturnedMoveCanBePlayed() {
        IState state = new FactoryCube().testState();

        AI ai = new MinimaxAI(Team.BLACK, 1);

        Action action = ai.chooseMove(state);

        assertNotNull(action);

        if (action instanceof Move move) {
            assertDoesNotThrow(() -> state.move(move));
        }
    }
}