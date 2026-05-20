package iut.gon.othello.model.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.gon.hexagonalcoordinates.CoordinateCube;
import iut.gon.hexagonalcoordinates.CoordinateDoubled;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.tokens.Ring;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

/**
 * Tests unitaires pour FactoryCube et FactoryDoubled.
 * Chemin : src/test/java/model/FactoryTest.java
 */


class FactoryTest {
 
    // =========================================================================
    // FactoryCube
    // =========================================================================
 
    @Test
    @DisplayName("FactoryCube.emptyState : retourne un IState non null")
    void cubeEmptyStateNotNull() {
        assertNotNull(new FactoryCube().emptyState());
    }
 
    @Test
    @DisplayName("FactoryCube.emptyState : terrain sans token")
    void cubeEmptyStateNoTokens() {
        IState s = new FactoryCube().emptyState();
        long tokenCount = s.board().values().stream()
                .filter(Objects::nonNull).count();
        assertEquals(0, tokenCount);
    }
 
    @Test
    @DisplayName("FactoryCube.emptyState : case centrale [0,0,0] présente")
    void cubeEmptyStateCenterExists() {
        IState s = new FactoryCube().emptyState();
        assertTrue(s.board().containsKey(new CoordinateCube(0, 0, 0)));
    }
 
    @Test
    @DisplayName("FactoryCube.stateForWhiteLineTest : contient au moins une ligne")
    void cubeWhiteLineTestHasLine() {
        IState s = new FactoryCube().stateForWhiteLineTest();
        assertFalse(s.lines().isEmpty());
    }
 
    @Test
    @DisplayName("FactoryCube.stateForBlackLineTest : contient au moins une ligne")
    void cubeBlackLineTestHasLine() {
        IState s = new FactoryCube().stateForBlackLineTest();
        assertTrue(s.lines().isEmpty());
    }
 
    @Test
    @DisplayName("FactoryCube.testState : état de jeu jouable (anneaux présents)")
    void cubeTestStateHasRings() {
        IState s = new FactoryCube().testState();
        long rings = s.board().values().stream()
                .filter(t -> t instanceof Ring).count();
        assertTrue(rings > 0);
    }
 
    @Test
    @DisplayName("FactoryCube.doubleLineStateTest : contient au moins 2 lignes")
    void cubeDoubleLineTestHasTwoLines() {
        IState s = new FactoryCube().doubleLineStateTest();
        assertTrue(s.lines().size() == 0);
    }
 
    // =========================================================================
    // FactoryDoubled
    // =========================================================================
 
    @Test
    @DisplayName("FactoryDoubled.emptyState : retourne un IState non null")
    void doubledEmptyStateNotNull() {
        assertNotNull(new FactoryDoubled().emptyState());
    }
 
    @Test
    @DisplayName("FactoryDoubled.emptyState : terrain sans token")
    void doubledEmptyStateNoTokens() {
        IState s = new FactoryDoubled().emptyState();
        long tokenCount = s.board().values().stream()
                .filter(Objects::nonNull).count();
        assertEquals(0, tokenCount);
    }
 
    @Test
    @DisplayName("FactoryDoubled.emptyState : case centrale [5,9] présente")
    void doubledEmptyStateCenterExists() {
        IState s = new FactoryDoubled().emptyState();
        assertTrue(s.board().containsKey(new CoordinateDoubled(5, 9)));
    }
 
    @Test
    @DisplayName("FactoryDoubled.stateForWhiteLineTest : contient au moins une ligne")
    void doubledWhiteLineTestHasLine() {
        IState s = new FactoryDoubled().stateForWhiteLineTest();
        assertFalse(s.lines().isEmpty());
    }
 
    @Test
    @DisplayName("FactoryDoubled.testState : anneaux présents")
    void doubledTestStateHasRings() {
        IState s = new FactoryDoubled().testState();
        long rings = s.board().values().stream()
                .filter(t -> t instanceof Ring).count();
        assertTrue(rings > 0);
    }
 
    // =========================================================================
    // Cohérence entre les deux factories
    // =========================================================================
 
    @Test
    @DisplayName("Les deux factories produisent un terrain de même taille (85 cases)")
    void bothFactoriesSameBoardSize() {
        IState cube    = new FactoryCube().emptyState();
        IState doubled = new FactoryDoubled().emptyState();
        assertEquals(cube.board().size(), doubled.board().size());
        assertEquals(85, cube.board().size());
    }
}