package iut.gon.othello.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.gon.hexagonalcoordinates.Coordinate;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.Map;
import java.util.NoSuchElementException;
 
/**
 * Tests unitaires pour Model.
 * Chemin : src/test/java/model/ModelTest.java
 *
 * Model stocke l'état courant et expose move/removeLine
 * qui modifient l'état interne (contrairement à IState qui est immutable).
 */

/*

class ModelTest {
 
    private Model model;
 
    @BeforeEach
    void setUp() {
        model = new Model(FactoryCube.testState());
    }
 
    // -------------------------------------------------------------------------
    // État initial
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("currentState : non null après construction")
    void currentStateNotNull() {
        assertNotNull(model.currentState());
    }
 
    @Test
    @DisplayName("currentState : board non null")
    void currentStateBoardNotNull() {
        assertNotNull(model.currentState().board());
    }
 
    // -------------------------------------------------------------------------
    // move (côté Model : void, modifie l'état interne)
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("move : l'état interne est mis à jour après un move valide")
    void moveUpdatesInternalState() {
        IState before = model.currentState();
        Coordinate ringPos = findFirstRingOf(before, before.turn());
        Coordinate target  = before.availableMoves(ringPos).iterator().next();
 
        model.move(new Move(ringPos, target));
 
        assertNotSame(before, model.currentState());
    }
 
    @Test
    @DisplayName("move : le tour change après un déplacement (sans ligne)")
    void moveSwitchesTurn() {
        IState before = model.currentState();
        Team teamBefore = before.turn();
        Coordinate ringPos = findFirstRingOf(before, teamBefore);
        Coordinate target  = before.availableMoves(ringPos).iterator().next();
 
        model.move(new Move(ringPos, target));
 
        // Si aucune ligne créée, le tour a changé
        if (model.currentState().lines().isEmpty()) {
            assertNotEquals(teamBefore, model.currentState().turn());
        }
    }
 
    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------
 
    private Coordinate findFirstRingOf(IState state, Team team) {
        return state.board().entrySet().stream()
                .filter(e -> e.getValue() instanceof Ring r && r.team() == team)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Aucun anneau de " + team));
    }
}

*/