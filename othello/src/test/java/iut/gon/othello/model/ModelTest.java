package iut.gon.othello.model;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.othello.model.factory.FactoryCube;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.tokens.Ring;

class ModelTest {
 
    private Model model;
 
    @BeforeEach
    void setUp() {
        model = new Model(new FactoryCube().testState());
    }

    @Test
    @DisplayName("currentState : non null après construction")
    void currentStateNotNull() {
        assertNotNull(model.getCurrentState());
    }
 
    @Test
    @DisplayName("currentState : board non null")
    void currentStateBoardNotNull() {
        assertNotNull(model.getCurrentState().board());
    }

    @Test
    @DisplayName("move : l'état interne est mis à jour après un move valide")
    void moveUpdatesInternalState() {
        IState before = model.getCurrentState();
        Coordinate ringPos = findFirstRingOf(before, before.turn());
        Coordinate target  = before.availableMoves(ringPos).iterator().next();
 
        model.moveRing(ringPos, target);
 
        assertNotSame(before, model.getCurrentState());
    }
 
    @Test
    @DisplayName("move : le tour change après un déplacement (sans ligne)")
    void moveSwitchesTurn() {
        IState before = model.getCurrentState();
        Team teamBefore = before.turn();
        Coordinate ringPos = findFirstRingOf(before, teamBefore);
        Coordinate target  = before.availableMoves(ringPos).iterator().next();
 
        model.moveRing(ringPos, target);
 
        if (model.getCurrentState().lines().isEmpty()) {
            assertNotEquals(teamBefore, model.getCurrentState().turn());
        }
    }
 
    private Coordinate findFirstRingOf(IState state, Team team) {
        return state.board().entrySet().stream()
                .filter(e -> e.getValue() instanceof Ring r && r.getTeam() == team)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Aucun anneau de " + team));
    }
}