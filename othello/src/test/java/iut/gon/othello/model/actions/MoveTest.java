package iut.gon.othello.model.actions;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
 
import iut.gon.hexagonalcoordinates.CoordinateCube;
import iut.gon.hexagonalcoordinates.Coordinate;
 
class MoveTest {

    @Test
    @DisplayName("Move : getFrom retourne la coordonnée de départ")
    void getFrom() {
        Coordinate from = new CoordinateCube(0, 0, 0);
        Coordinate to   = new CoordinateCube(1, -1, 0);
        Move move = new Move(from, to);
        assertEquals(from, move.getFrom());
    }
 
    @Test
    @DisplayName("Move : getTo retourne la coordonnée d'arrivée")
    void getTo() {
        Coordinate from = new CoordinateCube(0, 0, 0);
        Coordinate to   = new CoordinateCube(1, -1, 0);
        Move move = new Move(from, to);
        assertEquals(to, move.getTo());
    }

    @Test
    @DisplayName("Move : setFrom modifie la coordonnée de départ")
    void setFrom() {
        Coordinate from = new CoordinateCube(0, 0, 0);
        Coordinate to   = new CoordinateCube(1, -1, 0);
        Move move = new Move(from, to);
 
        Coordinate newFrom = new CoordinateCube(2, -1, -1);
        move.setFrom(newFrom);
        assertEquals(newFrom, move.getFrom());
    }
 
    @Test
    @DisplayName("Move : setTo modifie la coordonnée d'arrivée")
    void setTo() {
        Coordinate from = new CoordinateCube(0, 0, 0);
        Coordinate to   = new CoordinateCube(1, -1, 0);
        Move move = new Move(from, to);
 
        Coordinate newTo = new CoordinateCube(3, -2, -1);
        move.setTo(newTo);
        assertEquals(newTo, move.getTo());
    }

    @Test
    @DisplayName("Move est une Action")
    void moveIsAction() {
        Coordinate from = new CoordinateCube(0, 0, 0);
        Coordinate to   = new CoordinateCube(1, -1, 0);
        assertInstanceOf(Action.class, new Move(from, to));
    }
    
    @Test
    @DisplayName("Move : deux Move avec mêmes coordonnées sont égaux")
    void moveEquals() {
        Coordinate from = new CoordinateCube(0, 0, 0);
        Coordinate to   = new CoordinateCube(1, -1, 0);
        assertEquals(new Move(from, to), new Move(from, to));
    }
 
    @Test
    @DisplayName("Move : deux Move avec coordonnées différentes ne sont pas égaux")
    void moveNotEquals() {
        Coordinate from = new CoordinateCube(0, 0, 0);
        Coordinate to1  = new CoordinateCube(1, -1, 0);
        Coordinate to2  = new CoordinateCube(2, -1, -1);
        assertNotEquals(new Move(from, to1), new Move(from, to2));
    }
}
