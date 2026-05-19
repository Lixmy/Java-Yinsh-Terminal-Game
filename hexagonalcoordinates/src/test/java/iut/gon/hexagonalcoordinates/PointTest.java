package iut.gon.hexagonalcoordinates;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PointTest {

    @Test
    void testValues() {
        Point p = new Point(3, 7);

        assertEquals(3, p.x());
        assertEquals(7, p.y());
    }

    @Test
    void testToString() {
        Point p = new Point(3, 7);

        assertEquals("[3,7]", p.toString());
    }
}