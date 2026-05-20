package iut.gon.othello.model.actions;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
 
import iut.gon.hexagonalcoordinates.CoordinateCube;
import iut.gon.hexagonalcoordinates.Coordinate;
 
import java.util.HashSet;
import java.util.Set;
 
class RemoveLineTest {
 
    private Set<Coordinate> makeLine() {
        Set<Coordinate> line = new HashSet<>();
        line.add(new CoordinateCube(0,  0,  0));
        line.add(new CoordinateCube(1, -1,  0));
        line.add(new CoordinateCube(2, -2,  0));
        line.add(new CoordinateCube(3, -3,  0));
        line.add(new CoordinateCube(4, -4,  0));
        return line;
    }

    @Test
    @DisplayName("RemoveLine : getLine retourne la ligne")
    void getLine() {
        Set<Coordinate> line = makeLine();
        Coordinate ring = new CoordinateCube(5, -5, 0);
        RemoveLine rl = new RemoveLine(line, ring);
        assertEquals(line, rl.getLine());
    }
 
    @Test
    @DisplayName("RemoveLine : getRing retourne l'anneau")
    void getRing() {
        Set<Coordinate> line = makeLine();
        Coordinate ring = new CoordinateCube(5, -5, 0);
        RemoveLine rl = new RemoveLine(line, ring);
        assertEquals(ring, rl.getRing());
    }

    @Test
    @DisplayName("RemoveLine : setLine modifie la ligne")
    void setLine() {
        Set<Coordinate> line = makeLine();
        Coordinate ring = new CoordinateCube(5, -5, 0);
        RemoveLine rl = new RemoveLine(line, ring);
 
        Set<Coordinate> newLine = new HashSet<>();
        newLine.add(new CoordinateCube(0, 0,  0));
        newLine.add(new CoordinateCube(0, 1, -1));
        newLine.add(new CoordinateCube(0, 2, -2));
        newLine.add(new CoordinateCube(0, 3, -3));
        newLine.add(new CoordinateCube(0, 4, -4));
 
        rl.setLine(newLine);
        assertEquals(newLine, rl.getLine());
    }
 
    @Test
    @DisplayName("RemoveLine : setRing modifie l'anneau")
    void setRing() {
        Set<Coordinate> line = makeLine();
        Coordinate ring = new CoordinateCube(5, -5, 0);
        RemoveLine rl = new RemoveLine(line, ring);
 
        Coordinate newRing = new CoordinateCube(1, -1, 0);
        rl.setRing(newRing);
        assertEquals(newRing, rl.getRing());
    }

    @Test
    @DisplayName("RemoveLine est une Action")
    void removeLineIsAction() {
        assertInstanceOf(Action.class, new RemoveLine(makeLine(), new CoordinateCube(0, 0, 0)));
    }

    @Test
    @DisplayName("RemoveLine : la ligne contient 5 coordonnées")
    void lineFiveElements() {
        Set<Coordinate> line = makeLine();
        Coordinate ring = new CoordinateCube(5, -5, 0);
        RemoveLine rl = new RemoveLine(line, ring);
        assertEquals(5, rl.getLine().size());
    }
}
