package iut.gon.hexagonalcoordinates;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CoordinateDoubledTest {

    @Test
    void testConstructor() {
        CoordinateDoubled c = new CoordinateDoubled(5, 9);

        assertEquals(5, c.getY());
        assertEquals(9, c.getX());
    }

    @Test
    void testTo2DCoordinate() {
        CoordinateDoubled c = new CoordinateDoubled(5, 9);

        Point p = c.to2DCoordinate();

        assertEquals(9, p.x());
        assertEquals(5, p.y());
    }

    @Test
    void testPointyEastDirection() {
        CoordinateDoubled c = new CoordinateDoubled(5, 9);

        CoordinateDoubled result =
                (CoordinateDoubled) c.toDir(Mode.POINTY, Direction.E);

        assertEquals(new CoordinateDoubled(5, 11), result);
    }

    @Test
    void testPointyWestDirection() {
        CoordinateDoubled c = new CoordinateDoubled(5, 9);

        CoordinateDoubled result =
                (CoordinateDoubled) c.toDir(Mode.POINTY, Direction.O);

        assertEquals(new CoordinateDoubled(5, 7), result);
    }

    @Test
    void testFlatNorthDirection() {
        CoordinateDoubled c = new CoordinateDoubled(5, 9);

        CoordinateDoubled result =
                (CoordinateDoubled) c.toDir(Mode.FLAT, Direction.N);

        assertEquals(new CoordinateDoubled(3, 9), result);
    }

    @Test
    void testInvalidPointyDirection() {
        CoordinateDoubled c = new CoordinateDoubled(5, 9);

        assertThrows(
                InvalidParameterException.class,
                () -> c.toDir(Mode.POINTY, Direction.N)
        );
    }

    @Test
    void testInvalidFlatDirection() {
        CoordinateDoubled c = new CoordinateDoubled(5, 9);

        assertThrows(
                InvalidParameterException.class,
                () -> c.toDir(Mode.FLAT, Direction.E)
        );
    }

    @Test
    void testGetNeighborsSize() {
        CoordinateDoubled c = new CoordinateDoubled(5, 9);

        List<Coordinate> neighbors = c.getNeighbors(Mode.POINTY);

        assertEquals(6, neighbors.size());
    }

    @Test
    void testBetweenSameAxis() {
        CoordinateDoubled start = new CoordinateDoubled(5, 9);
        CoordinateDoubled end = new CoordinateDoubled(5, 15);

        List<Coordinate> result = start.between(Mode.POINTY, end);

        assertEquals(2, result.size());
        assertEquals(new CoordinateDoubled(5, 11), result.get(0));
        assertEquals(new CoordinateDoubled(5, 13), result.get(1));
    }

    @Test
    void testBetweenDifferentAxis() {
        CoordinateDoubled start = new CoordinateDoubled(5, 9);
        CoordinateDoubled end = new CoordinateDoubled(6, 12);

        assertThrows(
                DifferentAxisException.class,
                () -> start.between(Mode.POINTY, end)
        );
    }
}