package iut.gon.hexagonalcoordinates;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant une coordonnée dans un système de grille hexagonale.
 * Fournit des méthodes pour la navigation spatiale, la récupération des voisins 
 * et les déplacements selon différentes directions et modes d'orientation (POINTY ou FLAT).
 */
public abstract class Coordinate {
	/**
     * Convertit la coordonnée hexagonale en une coordonnée cartésienne 2D.
     *
     * @return L'objet {@link Point} représentant les coordonnées 2D équivalentes.
     */
	public abstract Point to2DCoordinate();
	
	/**
     * Calcule et retourne la coordonnée adjacente dans une direction donnée.
     *
     * @param mode      Le mode d'orientation de la grille hexagonale (POINTY ou FLAT).
     * @param direction La direction vers laquelle se déplacer.
     * @return La nouvelle {@link Coordinate} dans la direction spécifiée.
     */
    public abstract Coordinate toDir(Mode mode, Direction direction);
    
    /**
     * Récupère la liste des 6 coordonnées voisines adjacentes à la coordonnée courante,
     * en tenant compte du mode d'orientation de la grille.
     *
     * @param mode Le mode d'orientation de la grille (POINTY ou FLAT).
     * @return Une {@link List} contenant les 6 coordonnées voisines.
     */
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
    
    /**
     * Récupère la liste des coordonnées situées sur l'axe entre la coordonnée courante 
     * et la coordonnée de destination (excluant les bornes).
     *
     * @param mode Le mode d'orientation de la grille.
     * @param to   La coordonnée cible.
     * @return Une {@link List} des coordonnées situées strictement entre "this" et "to".
     * @throws DifferentAxisException Si les deux coordonnées ne sont pas alignées sur le même axe.
     */
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
    
    /**
     * Trouve la direction à emprunter pour atteindre une coordonnée cible depuis la coordonnée courante.
     *
     * @param mode Le mode d'orientation de la grille.
     * @param to   La coordonnée cible à atteindre.
     * @return La {@link Direction} menant à la coordonnée cible.
     * @throws DifferentAxisException Si les coordonnées ne sont pas sur le même axe ou sont trop éloignées (sécurité > 1000 itérations).
     */
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
    
    /**
     * Retourne la coordonnée adjacente au Nord-Ouest.
     *
     * @param mode Le mode de la grille.
     * @return La coordonnée au Nord-Ouest.
     */
    public Coordinate NO(Mode mode) {
        return toDir(mode, Direction.NO);
    }
    
    /**
     * Retourne la coordonnée adjacente au Nord-Est.
     *
     * @param mode Le mode de la grille.
     * @return La coordonnée au Nord-Est.
     */
    public Coordinate NE(Mode mode) {
        return toDir(mode, Direction.NE);
    }
    
    /**
     * Retourne la coordonnée adjacente à l'Est.
     *
     * @param mode Le mode de la grille (doit être POINTY).
     * @return La coordonnée à l'Est.
     * @throws InvalidParameterException Si le mode est FLAT (la direction E n'existe pas en mode FLAT).
     */
    public Coordinate E(Mode mode) {
        if (mode == Mode.FLAT) {
            throw new InvalidParameterException("La direction E n'existe pas en mode FLAT.");
        }
        return toDir(mode, Direction.E);
    }
    
    /**
     * Retourne la coordonnée adjacente à l'Ouest.
     *
     * @param mode Le mode de la grille (doit être POINTY).
     * @return La coordonnée à l'Ouest.
     * @throws InvalidParameterException Si le mode est FLAT (la direction O n'existe pas en mode FLAT).
     */
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
    
    /**
     * Retourne la coordonnée adjacente au Nord.
     *
     * @param mode Le mode de la grille (doit être FLAT).
     * @return La coordonnée au Nord.
     * @throws InvalidParameterException Si le mode est POINTY (la direction N n'existe pas en mode POINTY).
     */
    public Coordinate S(Mode mode) {
        if (mode == Mode.POINTY) {
            throw new InvalidParameterException("La direction S n'existe pas en mode POINTY.");
        }
        return toDir(mode, Direction.S);
    }
    
    /**
     * Retourne la coordonnée adjacente au Sud.
     *
     * @param mode Le mode de la grille (doit être FLAT).
     * @return La coordonnée au Sud.
     * @throws InvalidParameterException Si le mode est POINTY (la direction S n'existe pas en mode POINTY).
     */
    public Coordinate SO(Mode mode) {
        return toDir(mode, Direction.SO);
    }
    
    /**
     * Retourne la coordonnée adjacente au Sud-Est.
     *
     * @param mode Le mode de la grille.
     * @return La coordonnée au Sud-Est.
     */
    public Coordinate SE(Mode mode) {
        return toDir(mode, Direction.SE);
    }
}