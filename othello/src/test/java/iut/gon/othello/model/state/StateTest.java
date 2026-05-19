package iut.gon.othello.model.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.hexagonalcoordinates.CoordinateCube;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.*;
 
/**
 * Tests unitaires pour State (record).
 * Chemin : src/test/java/model/StateTest.java
 *
 * On suppose l'existence de :
 *   - FactoryCube ou FactoryDoubled pour créer un terrain vierge (emptyState)
 *   - Token, Pawn, Ring, Team (BLACK / WHITE)
 */

/*
 * 
class StateTest {
 
    private IState emptyState;
 
    @BeforeEach
    void setUp() {
        // Utiliser la factory appropriée selon votre implémentation
        emptyState = FactoryCube.emptyState();
        // ou : emptyState = FactoryDoubled.emptyState();
    }
 
    // -------------------------------------------------------------------------
    // Terrain vierge
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("emptyState : board non null")
    void emptyStateBoardNotNull() {
        assertNotNull(emptyState.board());
    }
 
    @Test
    @DisplayName("emptyState : aucun token sur le terrain")
    void emptyStateNoTokens() {
        long tokenCount = emptyState.board().values().stream()
                .filter(Objects::nonNull)
                .count();
        assertEquals(0, tokenCount);
    }
 
    @Test
    @DisplayName("emptyState : le terrain contient toutes les cases")
    void emptyStateBoardSize() {
        // Le plateau YINSH a 85 cases
        assertEquals(85, emptyState.board().size());
    }
 
    @Test
    @DisplayName("emptyState : turn retourne un Team non null")
    void emptyStateTurnNotNull() {
        assertNotNull(emptyState.turn());
    }
 
    // -------------------------------------------------------------------------
    // isInField
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("isInField : case centrale [0,0,0] est dans le terrain")
    void isInFieldCenter() {
        Coordinate center = new CoordinateCube(0, 0, 0);
        assertTrue(((State) emptyState).isInField(center));
    }
 
    @Test
    @DisplayName("isInField : coordonnée hors terrain → false")
    void isInFieldOutside() {
        Coordinate outside = new CoordinateCube(10, -5, -5);
        assertFalse(((State) emptyState).isInField(outside));
    }
 
    // -------------------------------------------------------------------------
    // winner
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("winner : retourne null en début de partie")
    void winnerNullAtStart() {
        assertNull(((State) emptyState).winner());
    }
 
    // -------------------------------------------------------------------------
    // move : exceptions
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("move : lever RuntimeException si une ligne existe déjà")
    void moveThrowsWhenLineExists() {
        // Utiliser un état avec une ligne préexistante
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
        Coordinate ringPos = findFirstRingOf(stateWithLine, Team.WHITE);
        Coordinate target = stateWithLine.availableMoves(ringPos).iterator().next();
 
        Move move = new Move(ringPos, target);
        assertThrows(RuntimeException.class, () -> stateWithLine.move(move));
    }
 
    @Test
    @DisplayName("move : lever IllegalArgumentException si la case de départ n'est pas un anneau du joueur courant")
    void moveThrowsWhenNotCurrentPlayerRing() {
        Coordinate emptyCell = findFirstEmptyCell(emptyState);
        Move move = new Move(emptyCell, emptyCell);
        assertThrows(IllegalArgumentException.class, () -> emptyState.move(move));
    }
 
    @Test
    @DisplayName("move : lever IndexOutOfBoundsException si coordonnée hors terrain")
    void moveThrowsWhenOutOfBounds() {
        Coordinate outside = new CoordinateCube(10, -5, -5);
        Move move = new Move(outside, outside);
        assertThrows(IndexOutOfBoundsException.class, () -> emptyState.move(move));
    }
 
    // -------------------------------------------------------------------------
    // move : comportement
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("move : retourne un nouvel IState (immutabilité)")
    void moveReturnsNewState() {
        IState withRings = FactoryCube.testState();
        Coordinate ringPos = findFirstRingOf(withRings, withRings.turn());
        Coordinate target = withRings.availableMoves(ringPos).iterator().next();
 
        IState newState = withRings.move(new Move(ringPos, target));
        assertNotSame(withRings, newState);
    }
 
    @Test
    @DisplayName("move : un pion est placé à la position de départ de l'anneau")
    void movePlacesPawnAtOrigin() {
        IState withRings = FactoryCube.testState();
        Coordinate ringPos = findFirstRingOf(withRings, withRings.turn());
        Coordinate target = withRings.availableMoves(ringPos).iterator().next();
 
        IState newState = withRings.move(new Move(ringPos, target));
        Token tokenAtOrigin = newState.board().get(ringPos);
 
        assertNotNull(tokenAtOrigin);
        assertInstanceOf(Pawn.class, tokenAtOrigin);
    }
 
    @Test
    @DisplayName("move : l'anneau se trouve à la position d'arrivée")
    void moveRingAtDestination() {
        IState withRings = FactoryCube.testState();
        Coordinate ringPos = findFirstRingOf(withRings, withRings.turn());
        Coordinate target = withRings.availableMoves(ringPos).iterator().next();
 
        IState newState = withRings.move(new Move(ringPos, target));
        Token tokenAtTarget = newState.board().get(target);
 
        assertNotNull(tokenAtTarget);
        assertInstanceOf(Ring.class, tokenAtTarget);
    }
 
    @Test
    @DisplayName("move : le tour change après un déplacement sans ligne")
    void moveSwitchesTurn() {
        IState withRings = FactoryCube.testState();
        Team currentTurn = withRings.turn();
        Coordinate ringPos = findFirstRingOf(withRings, currentTurn);
        Coordinate target = withRings.availableMoves(ringPos).iterator().next();
 
        IState newState = withRings.move(new Move(ringPos, target));
        // Si aucune ligne créée, le tour passe à l'adversaire
        if (newState.lines().isEmpty()) {
            assertNotEquals(currentTurn, newState.turn());
        }
    }
 
    // -------------------------------------------------------------------------
    // removeLine : exceptions
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("removeLine : lever RuntimeException si aucune ligne sur le plateau")
    void removeLineThrowsWhenNoLine() {
        IState withRings = FactoryCube.testState();
        // On suppose que testState n'a pas de ligne
        assertTrue(withRings.lines().isEmpty(), "testState ne devrait pas avoir de ligne");
 
        Set<Coordinate> fakeLine = new HashSet<>();
        Coordinate fakeRing = findFirstRingOf(withRings, withRings.turn());
        RemoveLine rl = new RemoveLine(fakeLine, fakeRing);
 
        assertThrows(RuntimeException.class, () -> withRings.removeLine(rl));
    }
 
    @Test
    @DisplayName("removeLine : lever RuntimeException si la ligne fournie est invalide (< 5 pions)")
    void removeLineThrowsWhenInvalidLine() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
 
        Set<Coordinate> invalidLine = new HashSet<>(List.of(
                new CoordinateCube(0, 0, 0)
        )); // 1 case seulement
        Coordinate ring = findFirstRingOf(stateWithLine, Team.WHITE);
        RemoveLine rl = new RemoveLine(invalidLine, ring);
 
        assertThrows(RuntimeException.class, () -> stateWithLine.removeLine(rl));
    }
 
    // -------------------------------------------------------------------------
    // removeLine : comportement
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("removeLine : retourne un nouvel état (immutabilité)")
    void removeLineReturnsNewState() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
        Set<Coordinate> line = stateWithLine.lines().get(0);
        Coordinate ring = findFirstRingOf(stateWithLine, Team.WHITE);
 
        IState newState = stateWithLine.removeLine(new RemoveLine(line, ring));
        assertNotSame(stateWithLine, newState);
    }
 
    @Test
    @DisplayName("removeLine : la ligne disparaît après suppression")
    void removeLineRemovesLine() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
        Set<Coordinate> line = stateWithLine.lines().get(0);
        Coordinate ring = findFirstRingOf(stateWithLine, Team.WHITE);
 
        IState newState = stateWithLine.removeLine(new RemoveLine(line, ring));
        assertFalse(newState.lines().contains(line));
    }
 
    // -------------------------------------------------------------------------
    // lines
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("lines : stateForWhiteLineTest contient au moins une ligne blanche")
    void linesWhiteLineTest() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
        assertFalse(stateWithLine.lines().isEmpty());
    }
 
    @Test
    @DisplayName("lines : chaque ligne contient exactement 5 coordonnées")
    void linesExactlyFiveCoords() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
        for (Set<Coordinate> line : stateWithLine.lines()) {
            assertEquals(5, line.size(), "Une ligne doit contenir exactement 5 cases");
        }
    }
 
    @Test
    @DisplayName("lines : doubleLineStateTest contient au moins 2 lignes")
    void linesDoubleLineTest() {
        IState doubleState = FactoryCube.doubleLineStateTest();
        assertTrue(doubleState.lines().size() >= 2);
    }
 
    // -------------------------------------------------------------------------
    // availableMoves
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("availableMoves : anneau isolé sur terrain vide peut se déplacer")
    void availableMovesNotEmpty() {
        IState withRings = FactoryCube.testState();
        Coordinate ringPos = findFirstRingOf(withRings, withRings.turn());
        Set<Coordinate> moves = withRings.availableMoves(ringPos);
        assertFalse(moves.isEmpty());
    }
 
    @Test
    @DisplayName("availableMoves : case vide → ensemble vide")
    void availableMovesEmptyCell() {
        Coordinate emptyCell = findFirstEmptyCell(emptyState);
        Set<Coordinate> moves = emptyState.availableMoves(emptyCell);
        assertTrue(moves.isEmpty());
    }
 
    // -------------------------------------------------------------------------
    // getPawnsLines (méthode statique)
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("getPawnsLines : terrain vide → aucune ligne")
    void getPawnsLinesEmptyBoard() {
        List<Set<Coordinate>> lines = State.getPawnsLines(emptyState.board());
        assertTrue(lines.isEmpty());
    }
 
    @Test
    @DisplayName("getPawnsLines : terrain avec ligne blanche → retourne cette ligne")
    void getPawnsLinesWithLine() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
        List<Set<Coordinate>> lines = State.getPawnsLines(stateWithLine.board());
        assertFalse(lines.isEmpty());
    }
 
    // -------------------------------------------------------------------------
    // equals & hashCode (hérité du record)
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("equals : deux états identiques sont égaux")
    void equalsIdenticalStates() {
        IState s1 = FactoryCube.emptyState();
        IState s2 = FactoryCube.emptyState();
        assertEquals(s1, s2);
    }
 
    @Test
    @DisplayName("hashCode : deux états identiques ont le même hashCode")
    void hashCodeIdenticalStates() {
        IState s1 = FactoryCube.emptyState();
        IState s2 = FactoryCube.emptyState();
        assertEquals(s1.hashCode(), s2.hashCode());
    }
 
    // -------------------------------------------------------------------------
    // Helpers privés
    // -------------------------------------------------------------------------
 
    private Coordinate findFirstRingOf(IState state, Team team) {
        return state.board().entrySet().stream()
                .filter(e -> e.getValue() instanceof Ring r && r.team() == team)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Aucun anneau de " + team));
    }
 
    private Coordinate findFirstEmptyCell(IState state) {
        return state.board().entrySet().stream()
                .filter(e -> e.getValue() == null)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Aucune case vide"));
    }
}

*/
