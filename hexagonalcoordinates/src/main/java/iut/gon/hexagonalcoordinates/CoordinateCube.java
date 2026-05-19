package iut.gon.hexagonalcoordinates;

import java.security.InvalidParameterException;
import java.util.Objects;

public class CoordinateCube extends Coordinate {
    private final int q;
    private final int r;
    private final int s;

    public CoordinateCube(int q, int r, int s) {
        if (q + r + s != 0) {
            throw new IllegalArgumentException("q + r + s doit être égal à 0.");
        }
        this.q = q;
        this.r = r;
        this.s = s;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public int getS() {
        return s;
    }

    @Override
    public Point to2DCoordinate() {
        int x = 2 * q + r;
        int y = r;
        return new Point(x, y);
    }

    @Override
    public Coordinate toDir(Mode mode, Direction direction) {
        return switch (mode) {
            case POINTY -> switch (direction) {
                case NO -> new CoordinateCube(q, r - 1, s + 1);
                case NE -> new CoordinateCube(q + 1, r - 1, s);
                case E -> new CoordinateCube(q + 1, r, s - 1);
                case SE -> new CoordinateCube(q, r + 1, s - 1);
                case SO -> new CoordinateCube(q - 1, r + 1, s);
                case O -> new CoordinateCube(q - 1, r, s + 1);
                default -> throw new InvalidParameterException("Direction invalide pour POINTY");
            };
            case FLAT -> switch (direction) {
                case N -> new CoordinateCube(q, r - 1, s + 1);
                case NE -> new CoordinateCube(q + 1, r - 1, s);
                case SE -> new CoordinateCube(q + 1, r, s - 1);
                case S -> new CoordinateCube(q, r + 1, s - 1);
                case SO -> new CoordinateCube(q - 1, r + 1, s);
                case NO -> new CoordinateCube(q - 1, r, s + 1);
                default -> throw new InvalidParameterException("Direction invalide pour FLAT");
            };
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CoordinateCube other)) return false;
        return q == other.q && r == other.r && s == other.s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, r, s);
    }

    @Override
    public String toString() {
        return "[" + q + "," + r + "," + s + "]";
    }
}
