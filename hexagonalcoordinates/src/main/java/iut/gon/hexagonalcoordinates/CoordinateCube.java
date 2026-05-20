package iut.gon.hexagonalcoordinates;

import java.security.InvalidParameterException;
import java.util.Objects;

/**
 * Représente une coordonnée cubique dans un système de grille hexagonale.
 * Ce système utilise trois axes (q, r, s) pour repérer un hexagone.
 * La contrainte fondamentale de ce système est que la somme des trois composantes 
 * doit toujours être égale à 0 (q + r + s = 0).
 */
public class CoordinateCube extends Coordinate {
    private final int q;
    private final int r;
    private final int s;

    /**
     * Construit une nouvelle coordonnée cubique à partir de ses trois axes.
     *
     * @param q La position sur l'axe q.
     * @param r La position sur l'axe r.
     * @param s La position sur l'axe s.
     * @throws IllegalArgumentException Si la somme de q, r et s n'est pas égale à 0.
     */
    public CoordinateCube(int q, int r, int s) {
        if (q + r + s != 0) {
            throw new IllegalArgumentException("q + r + s doit être égal à 0.");
        }
        this.q = q;
        this.r = r;
        this.s = s;
    }

    /**
     * Récupère la valeur de l'axe q.
     *
     * @return L'entier représentant la position sur l'axe q.
     */
    public int getQ() {
        return q;
    }

    /**
     * Récupère la valeur de l'axe r.
     *
     * @return L'entier représentant la position sur l'axe r.
     */
    public int getR() {
        return r;
    }

    /**
     * Récupère la valeur de l'axe s.
     *
     * @return L'entier représentant la position sur l'axe s.
     */
    public int getS() {
        return s;
    }

    /**
     * Convertit la coordonnée cubique en une coordonnée cartésienne 2D.
     * Le calcul applique une transformation spécifique aux axes r, q et s.
     *
     * @return Un objet {@link Point} représentant les coordonnées 2D équivalentes.
     */
    @Override
    public Point to2DCoordinate() {
        int x = 5 + r;
        int y = 9 + q - s;
        return new Point(x, y);
    }

    /**
     * Calcule et retourne la coordonnée cubique adjacente dans une direction donnée.
     *
     * @param mode      Le mode d'orientation de la grille (POINTY ou FLAT).
     * @param direction La direction vers laquelle se déplacer.
     * @return Une nouvelle instance de {@link CoordinateCube} représentant la case voisine.
     * @throws InvalidParameterException Si la direction fournie n'est pas valide pour le mode donné.
     */
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

    /**
     * Compare cette coordonnée cubique avec un autre objet pour vérifier leur égalité.
     *
     * @param obj L'objet à comparer avec cette coordonnée.
     * @return {@code true} si l'objet est une {@link CoordinateCube} avec les mêmes valeurs (q, r, s), {@code false} sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CoordinateCube other)) return false;
        return q == other.q && r == other.r && s == other.s;
    }

    /**
     * Génère un code de hachage pour cette coordonnée basé sur ses trois axes.
     *
     * @return Le code de hachage généré.
     */
    @Override
    public int hashCode() {
        return Objects.hash(q, r, s);
    }

    /**
     * Retourne une représentation textuelle de la coordonnée cubique.
     * Le format généré est de la forme "[q,r,s]".
     *
     * @return La chaîne de caractères représentant la coordonnée.
     */
    @Override
    public String toString() {
        return "[" + q + "," + r + "," + s + "]";
    }
}