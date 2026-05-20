package iut.gon.othello.model.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.hexagonalcoordinates.CoordinateCube;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.factory.FactoryCube;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

import java.util.Map;
import java.util.Set;

class StateTest {

    private IState emptyState;
    private IState testState;

    @BeforeEach
    void setUp() {
        emptyState = FactoryCube.emptyState();
        testState  = FactoryCube.testState();
    }

    @Test
    @DisplayName("emptyState : board non null")
    void emptyStateBoardNotNull() {
        assertNotNull(emptyState.board());
    }

    @Test
    @DisplayName("emptyState : aucun token sur le terrain")
    void emptyStateNoTokens() {
        long tokenCount = emptyState.board().values().stream()
                .filter(t -> t != null)
                .count();
        assertEquals(0, tokenCount);
    }

    @Test
    @DisplayName("emptyState : le terrain contient 85 cases")
    void emptyStateBoardSize() {
        assertEquals(85, emptyState.board().size());
    }

    @Test
    @DisplayName("emptyState : turn non null")
    void emptyStateTurnNotNull() {
        assertNotNull(emptyState.turn());
    }

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

    @Test
    @DisplayName("winner : retourne null en début de partie")
    void winnerNullAtStart() {
        assertNull(((State) emptyState).winner());
    }
    
    @Test
    @DisplayName("lines : emptyState n'a aucune ligne")
    void linesEmptyState() {
        assertTrue(emptyState.lines().isEmpty());
    }

    @Test
    @DisplayName("lines : stateForWhiteLineTest contient au moins une ligne")
    void linesWhiteLineTest() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
        assertFalse(stateWithLine.lines().isEmpty());
    }

    @Test
    @DisplayName("lines : chaque ligne contient exactement 5 coordonnées")
    void linesExactlyFiveCoords() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
        for (Set<Coordinate> line : stateWithLine.lines()) {
            assertEquals(5, line.size());
        }
    }

    @Test
    @DisplayName("lines : doubleLineStateTest contient au moins 2 lignes")
    void linesDoubleLineTest() {
        IState doubleState = FactoryCube.doubleLineStateTest();
        assertTrue(doubleState.lines().size() >= 2);
    }

    @Test
    @DisplayName("move : lever RuntimeException si une ligne existe déjà")
    void moveThrowsWhenLineExists() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();

        Coordinate ringPos = null;
        for (Map.Entry<Coordinate, Token> e : stateWithLine.board().entrySet()) {
            if (e.getValue() instanceof Ring && e.getValue().getTeam() == stateWithLine.turn()) {
                ringPos = e.getKey();
                break;
            }
        }
        assertNotNull(ringPos);
        Coordinate target = stateWithLine.availableMoves(ringPos).iterator().next();
        Move move = new Move(ringPos, target);
        final Coordinate finalRingPos = ringPos;
        assertThrows(RuntimeException.class, () -> stateWithLine.move(move));
    }

    @Test
    @DisplayName("move : lever IndexOutOfBoundsException si coordonnée hors terrain")
    void moveThrowsWhenOutOfBounds() {
        Coordinate outside = new CoordinateCube(10, -5, -5);
        Coordinate center  = new CoordinateCube(0, 0, 0);
        Move move = new Move(outside, center);
        assertThrows(IndexOutOfBoundsException.class, () -> emptyState.move(move));
    }

    @Test
    @DisplayName("move : retourne un nouvel IState (immutabilité)")
    void moveReturnsNewState() {

        Coordinate ringPos = null;
        for (Map.Entry<Coordinate, Token> e : testState.board().entrySet()) {
            if (e.getValue() instanceof Ring && e.getValue().getTeam() == testState.turn()) {
                ringPos = e.getKey();
                break;
            }
        }
        assertNotNull(ringPos);
        Coordinate target = testState.availableMoves(ringPos).iterator().next();
        IState newState = testState.move(new Move(ringPos, target));
        assertNotSame(testState, newState);
    }
    
    @Test
    @DisplayName("removeLine : lever RuntimeException si aucune ligne")
    void removeLineThrowsWhenNoLine() {
        assertTrue(testState.lines().isEmpty());
        Set<Coordinate> fakeLine = FactoryCube.stateForWhiteLineTest().lines().get(0);
        Coordinate fakeRing = null;
        for (Map.Entry<Coordinate, Token> e : testState.board().entrySet()) {
            if (e.getValue() instanceof Ring) {
                fakeRing = e.getKey();
                break;
            }
        }
        assertNotNull(fakeRing);
        final Coordinate ring = fakeRing;
        final Set<Coordinate> line = fakeLine;
        assertThrows(RuntimeException.class, () -> testState.removeLine(new RemoveLine(line, ring)));
    }

    @Test
    @DisplayName("removeLine : retourne un nouvel état (immutabilité)")
    void removeLineReturnsNewState() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
        Set<Coordinate> line = stateWithLine.lines().get(0);
        Coordinate ring = null;
        for (Map.Entry<Coordinate, Token> e : stateWithLine.board().entrySet()) {
            if (e.getValue() instanceof Ring && e.getValue().getTeam() == stateWithLine.turn()) {
                ring = e.getKey();
                break;
            }
        }
        assertNotNull(ring);
        IState newState = stateWithLine.removeLine(new RemoveLine(line, ring));
        assertNotSame(stateWithLine, newState);
    }

    @Test
    @DisplayName("getPawnsLines : terrain vide → aucune ligne")
    void getPawnsLinesEmptyBoard() {
        assertTrue(IState.getPawnsLines(emptyState.board()).isEmpty());
    }

    @Test
    @DisplayName("getPawnsLines : terrain avec ligne → retourne cette ligne")
    void getPawnsLinesWithLine() {
        IState stateWithLine = FactoryCube.stateForWhiteLineTest();
        assertFalse(IState.getPawnsLines(stateWithLine.board()).isEmpty());
    }

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
}