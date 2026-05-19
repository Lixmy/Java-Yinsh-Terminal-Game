package iut.gon.hexagonalcoordinates;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;



public abstract class Coordinate {
	public abstract Point to2DCoordinate();

    public abstract Coordinate toDir(Mode mode, Direction direction);

    public List<Coordinate> getNeighbors(Mode mode) {
        List<Coordinate> neighbors = new ArrayList<>();

        if (mode == Mode.POINTY) {
            neighbors.add(NO(mode));
            neighbors.add(NE(mode));
            neighbors.add(E(mode));
            neighbors.add(SE(mode));
            neighbors.add(SO(mode));
            neighbors.add(O(mode));
        } else {
            neighbors.add(N(mode));
            neighbors.add(NE(mode));
            neighbors.add(SE(mode));
            neighbors.add(S(mode));
            neighbors.add(SO(mode));
            neighbors.add(NO(mode));
        }

        return neighbors;
    }

    public List<Coordinate> between(Mode mode, Coordinate to) {
        Direction direction = findDirection(mode, to);
        List<Coordinate> result = new ArrayList<>();

        Coordinate current = this.toDir(mode, direction);
        while (!current.equals(to)) {
            result.add(current);
            current = current.toDir(mode, direction);
        }

        return result;
    }

    private Direction findDirection(Mode mode, Coordinate to) {
        Direction[] directions = mode == Mode.POINTY
                ? new Direction[]{Direction.NO, Direction.NE, Direction.E, Direction.SE, Direction.SO, Direction.O}
                : new Direction[]{Direction.N, Direction.NE, Direction.SE, Direction.S, Direction.SO, Direction.NO};

        for (Direction direction : directions) {
            Coordinate current = this.toDir(mode, direction);
            int security = 0;

            while (security < 1000) {
                if (current.equals(to)) {
                    return direction;
                }
                current = current.toDir(mode, direction);
                security++;
            }
        }

        throw new DifferentAxisException("Les deux coordonnées ne sont pas sur le même axe.");
    }

    public Coordinate NO(Mode mode) {
        return toDir(mode, Direction.NO);
    }

    public Coordinate NE(Mode mode) {
        return toDir(mode, Direction.NE);
    }

    public Coordinate E(Mode mode) {
        if (mode == Mode.FLAT) {
            throw new InvalidParameterException("La direction E n'existe pas en mode FLAT.");
        }
        return toDir(mode, Direction.E);
    }

    public Coordinate O(Mode mode) {
        if (mode == Mode.FLAT) {
            throw new InvalidParameterException("La direction O n'existe pas en mode FLAT.");
        }
        return toDir(mode, Direction.O);
    }

    public Coordinate N(Mode mode) {
        if (mode == Mode.POINTY) {
            throw new InvalidParameterException("La direction N n'existe pas en mode POINTY.");
        }
        return toDir(mode, Direction.N);
    }

    public Coordinate S(Mode mode) {
        if (mode == Mode.POINTY) {
            throw new InvalidParameterException("La direction S n'existe pas en mode POINTY.");
        }
        return toDir(mode, Direction.S);
    }

    public Coordinate SO(Mode mode) {
        return toDir(mode, Direction.SO);
    }

    public Coordinate SE(Mode mode) {
        return toDir(mode, Direction.SE);
    }
}
}
