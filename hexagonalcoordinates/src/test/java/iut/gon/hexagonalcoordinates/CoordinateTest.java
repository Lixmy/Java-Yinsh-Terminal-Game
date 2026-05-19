package iut.gon.hexagonalcoordinates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CoordinateTest {

    @Test
    void testShortcutMethodsPointy() {
        CoordinateDoubled c = new CoordinateDoubled(5, 9);

        assertEquals(new CoordinateDoubled(5, 11), c.E(Mode.POINTY));
        assertEquals(new CoordinateDoubled(5, 7), c.O(Mode.POINTY));
        assertEquals(new CoordinateDoubled(4, 10), c.NE(Mode.POINTY));
        assertEquals(new CoordinateDoubled(4, 8), c.NO(Mode.POINTY));
        assertEquals(new CoordinateDoubled(6, 10), c.SE(Mode.POINTY));
        assertEquals(new CoordinateDoubled(6, 8), c.SO(Mode.POINTY));
    }
}