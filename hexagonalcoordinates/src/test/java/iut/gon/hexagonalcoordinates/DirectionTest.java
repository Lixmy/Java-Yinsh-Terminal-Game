package iut.gon.hexagonalcoordinates;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    void testOpposite() {
        assertEquals(Direction.SE, Direction.NO.opposite());
        assertEquals(Direction.S, Direction.N.opposite());
        assertEquals(Direction.SO, Direction.NE.opposite());
        assertEquals(Direction.O, Direction.E.opposite());
        assertEquals(Direction.NO, Direction.SE.opposite());
        assertEquals(Direction.N, Direction.S.opposite());
        assertEquals(Direction.NE, Direction.SO.opposite());
        assertEquals(Direction.E, Direction.O.opposite());
    }
}
