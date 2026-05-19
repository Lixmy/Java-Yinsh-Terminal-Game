package iut.gon.othello.model.tokens;
 
import org.junit.jupiter.api.Test;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.hexagonalcoordinates.CoordinateCube;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.Set;
 
/**
 * Tests unitaires pour Move et RemoveLine.
 * Chemin : src/test/java/model/ActionTest.java
 */

/*

class ActionTest {
 
    // -------------------------------------------------------------------------
    // Move
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("Move : stocke correctement les coordonnées de départ et d'arrivée")
    void moveStoresCoordinates() {
        Coordinate from = new CoordinateCube(0, 0, 0);
        Coordinate to   = new CoordinateCube(1, -1, 0);
        Move move = new Move(from, to);
 
        assertEquals(from, move.getFrom());
        assertEquals(to,   move.getTo());
    }
 
    @Test
    @DisplayName("Move est une Action")
    void moveIsAction() {
        Coordinate from = new CoordinateCube(0, 0, 0);
        Coordinate to   = new CoordinateCube(1, -1, 0);
        assertInstanceOf(Action.class, new Move(from, to));
    }
 
    @Test
    @DisplayName("Move : deux Move identiques sont equals")
    void moveEquals() {
        Coordinate from = new CoordinateCube(0, 0, 0);
        Coordinate to   = new CoordinateCube(1, -1, 0);
        assertEquals(new Move(from, to), new Move(from, to));
    }
 
    // -------------------------------------------------------------------------
    // RemoveLine
    // -------------------------------------------------------------------------
 
    @Test
    @DisplayName("RemoveLine : stocke correctement la ligne et l'anneau")
    void removeLineStoresData() {
        Set<Coordinate> line = Set.of(
                new CoordinateCube(0,  0,  0),
                new CoordinateCube(1, -1,  0),
                new CoordinateCube(2, -2,  0),
                new CoordinateCube(3, -3,  0),
                new CoordinateCube(4, -4,  0)
        );
        Coordinate ring = new CoordinateCube(5, -5, 0);
        RemoveLine rl = new RemoveLine(line, ring);
 
        assertEquals(line, rl.getLine());
        assertEquals(ring, rl.getRing());
    }
 
    @Test
    @DisplayName("RemoveLine est une Action")
    void removeLineIsAction() {
        Set<Coordinate> line = Set.of(
                new CoordinateCube(0, 0,  0),
                new CoordinateCube(0, 1, -1),
                new CoordinateCube(0, 2, -2),
                new CoordinateCube(0, 3, -3),
                new CoordinateCube(0, 4, -4)
        );
        Coordinate ring = new CoordinateCube(1, -1, 0);
        assertInstanceOf(Action.class, new RemoveLine(line, ring));
    }
 
    @Test
    @DisplayName("Move et RemoveLine sont des types distincts d'Action")
    void moveAndRemoveLineDistinct() {
        Coordinate c = new CoordinateCube(0, 0, 0);
        Action move = new Move(c, c);
        Action rl   = new RemoveLine(Set.of(), c);
        assertNotEquals(move.getClass(), rl.getClass());
    }
}

*/
