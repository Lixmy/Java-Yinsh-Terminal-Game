package iut.gon.othello.model.factory;

import org.junit.jupiter.api.Test;

import iut.gon.hexagonalcoordinates.CoordinateDoubled;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.Objects;
 
/**
 * Tests unitaires pour FactoryCube et FactoryDoubled.
 * Chemin : src/test/java/model/FactoryTest.java
 */

/*

class FactoryTest {
 
    // =========================================================================
    // FactoryCube
    // =========================================================================
 
    @Test
    @DisplayName("FactoryCube.emptyState : retourne un IState non null")
    void cubeEmptyStateNotNull() {
        assertNotNull(FactoryCube.emptyState());
    }
 
    @Test
    @DisplayName("FactoryCube.emptyState : terrain sans token")
    void cubeEmptyStateNoTokens() {
        IState s = FactoryCube.emptyState();
        long tokenCount = s.board().values().stream()
                .filter(Objects::nonNull).count();
        assertEquals(0, tokenCount);
    }
 
    @Test
    @DisplayName("FactoryCube.emptyState : case centrale [0,0,0] présente")
    void cubeEmptyStateCenterExists() {
        IState s = FactoryCube.emptyState();
        assertTrue(s.board().containsKey(new CoordinateCube(0, 0, 0)));
    }
 
    @Test
    @DisplayName("FactoryCube.stateForWhiteLineTest : contient au moins une ligne")
    void cubeWhiteLineTestHasLine() {
        IState s = FactoryCube.stateForWhiteLineTest();
        assertFalse(s.lines().isEmpty());
    }
 
    @Test
    @DisplayName("FactoryCube.stateForBlackLineTest : contient au moins une ligne")
    void cubeBlackLineTestHasLine() {
        IState s = FactoryCube.stateForBlackLineTest();
        assertFalse(s.lines().isEmpty());
    }
 
    @Test
    @DisplayName("FactoryCube.testState : état de jeu jouable (anneaux présents)")
    void cubeTestStateHasRings() {
        IState s = FactoryCube.testState();
        long rings = s.board().values().stream()
                .filter(t -> t instanceof Ring).count();
        assertTrue(rings > 0);
    }
 
    @Test
    @DisplayName("FactoryCube.doubleLineStateTest : contient au moins 2 lignes")
    void cubeDoubleLineTestHasTwoLines() {
        IState s = FactoryCube.doubleLineStateTest();
        assertTrue(s.lines().size() >= 2);
    }
 
    // =========================================================================
    // FactoryDoubled
    // =========================================================================
 
    @Test
    @DisplayName("FactoryDoubled.emptyState : retourne un IState non null")
    void doubledEmptyStateNotNull() {
        assertNotNull(FactoryDoubled.emptyState());
    }
 
    @Test
    @DisplayName("FactoryDoubled.emptyState : terrain sans token")
    void doubledEmptyStateNoTokens() {
        IState s = FactoryDoubled.emptyState();
        long tokenCount = s.board().values().stream()
                .filter(Objects::nonNull).count();
        assertEquals(0, tokenCount);
    }
 
    @Test
    @DisplayName("FactoryDoubled.emptyState : case centrale [5,9] présente")
    void doubledEmptyStateCenterExists() {
        IState s = FactoryDoubled.emptyState();
        assertTrue(s.board().containsKey(new CoordinateDoubled(5, 9)));
    }
 
    @Test
    @DisplayName("FactoryDoubled.stateForWhiteLineTest : contient au moins une ligne")
    void doubledWhiteLineTestHasLine() {
        IState s = FactoryDoubled.stateForWhiteLineTest();
        assertFalse(s.lines().isEmpty());
    }
 
    @Test
    @DisplayName("FactoryDoubled.testState : anneaux présents")
    void doubledTestStateHasRings() {
        IState s = FactoryDoubled.testState();
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
        IState cube    = FactoryCube.emptyState();
        IState doubled = FactoryDoubled.emptyState();
        assertEquals(cube.board().size(), doubled.board().size());
        assertEquals(85, cube.board().size());
    }
}
 
*/