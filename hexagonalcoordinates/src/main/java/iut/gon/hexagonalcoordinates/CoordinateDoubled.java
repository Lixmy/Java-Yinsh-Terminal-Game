package iut.gon.hexagonalcoordinates;

import java.security.InvalidParameterException;
import java.util.Objects;

public class CoordinateDoubled extends Coordinate {
    private final int y;
    private final int x;

    public CoordinateDoubled(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public Point to2DCoordinate() {
        return new Point(x, y);
    }

    @Override
    public Coordinate toDir(Mode mode, Direction direction) {
        return switch (mode) {
            case POINTY -> switch (direction) {
                case NO -> new CoordinateDoubled(y - 1, x - 1);
                case NE -> new CoordinateDoubled(y - 1, x + 1);
                case E -> new CoordinateDoubled(y, x + 2);
                case SE -> new CoordinateDoubled(y + 1, x + 1);
                case SO -> new CoordinateDoubled(y + 1, x - 1);
                case O -> new CoordinateDoubled(y, x - 2);
                default -> throw new InvalidParameterException("Direction invalide pour POINTY");
            };
            case FLAT -> switch (direction) {
                case N -> new CoordinateDoubled(y - 2, x);
                case NE -> new CoordinateDoubled(y - 1, x + 1);
                case SE -> new CoordinateDoubled(y + 1, x + 1);
                case S -> new CoordinateDoubled(y + 2, x);
                case SO -> new CoordinateDoubled(y + 1, x - 1);
                case NO -> new CoordinateDoubled(y - 1, x - 1);
                default -> throw new InvalidParameterException("Direction invalide pour FLAT");
            };
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CoordinateDoubled other)) return false;
        return y == other.y && x == other.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override
    public String toString() {
        return "[" + y + "," + x + "]";
    }
}