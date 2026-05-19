package iut.gon.hexagonalcoordinates;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CoordinateCubeTest {

    @Test
    void testConstructorValid() {
        CoordinateCube c = new CoordinateCube(1, -1, 0);

        assertEquals(1, c.getQ());
        assertEquals(-1, c.getR());
        assertEquals(0, c.getS());
    }

    @Test
    void testConstructorInvalid() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CoordinateCube(1, 1, 1)
        );
    }

    @Test
    void testDirectionEPointy() {
        CoordinateCube c = new CoordinateCube(0, 0, 0);

        CoordinateCube result =
                (CoordinateCube) c.toDir(Mode.POINTY, Direction.E);

        assertEquals(new CoordinateCube(1, 0, -1), result);
    }

    @Test
    void testDirectionOPointy() {
        CoordinateCube c = new CoordinateCube(0, 0, 0);

        CoordinateCube result =
                (CoordinateCube) c.toDir(Mode.POINTY, Direction.O);

        assertEquals(new CoordinateCube(-1, 0, 1), result);
    }

    @Test
    void testGetNeighborsSize() {
        CoordinateCube c = new CoordinateCube(0, 0, 0);

        List<Coordinate> neighbors = c.getNeighbors(Mode.POINTY);

        assertEquals(6, neighbors.size());
    }

    @Test
    void testBetweenSameAxis() {
        CoordinateCube start = new CoordinateCube(0, 0, 0);
        CoordinateCube end = new CoordinateCube(3, 0, -3);

        List<Coordinate> result = start.between(Mode.POINTY, end);

        assertEquals(2, result.size());
        assertEquals(new CoordinateCube(1, 0, -1), result.get(0));
        assertEquals(new CoordinateCube(2, 0, -2), result.get(1));
    }

    @Test
    void testBetweenDifferentAxis() {
        CoordinateCube start = new CoordinateCube(0, 0, 0);
        CoordinateCube end = new CoordinateCube(2, -1, -1);

        assertThrows(
                DifferentAxisException.class,
                () -> start.between(Mode.POINTY, end)
        );
    }

    @Test
    void testTo2DCoordinate() {
        CoordinateCube c = new CoordinateCube(0, 0, 0);

        Point p = c.to2DCoordinate();

        assertEquals(0, p.x());
        assertEquals(0, p.y());
    }
}