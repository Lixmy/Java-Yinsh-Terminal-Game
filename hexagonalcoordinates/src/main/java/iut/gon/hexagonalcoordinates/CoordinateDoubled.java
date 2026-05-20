package iut.gon.hexagonalcoordinates;

import java.security.InvalidParameterException;
import java.util.Objects;

/**
 * Représente une coordonnée dans un système de "coordonnées doublées" (Doubled Coordinates) 
 * pour une grille hexagonale.
 * Ce système s'appuie sur des coordonnées (x, y) où l'espacement sur l'un des axes 
 * est doublé par rapport à une grille carrée standard, ce qui permet de manipuler 
 * la grille hexagonale avec des entiers tout en conservant une logique de lignes et colonnes.
 */
public class CoordinateDoubled extends Coordinate {
    private final int y;
    private final int x;

    /**
     * Construit une nouvelle coordonnée doublée à partir de ses composantes y et x.
     *
     * @param y L'ordonnée (la ligne) de la coordonnée.
     * @param x L'abscisse (la colonne) de la coordonnée.
     */
    public CoordinateDoubled(int y, int x) {
        this.y = y;
        this.x = x;
    }

    /**
     * Récupère la valeur de la composante y (la ligne).
     *
     * @return L'entier représentant l'ordonnée.
     */
    public int getY() {
        return y;
    }

    /**
     * Récupère la valeur de la composante x (la colonne).
     *
     * @return L'entier représentant l'abscisse.
     */
    public int getX() {
        return x;
    }

    /**
     * Convertit cette coordonnée doublée en une coordonnée cartésienne 2D classique.
     *
     * @return Un objet {@link Point} contenant les valeurs x et y de cette coordonnée.
     */
    @Override
    public Point to2DCoordinate() {
        return new Point(x, y);
    }

    /**
     * Calcule et retourne la coordonnée doublée adjacente dans une direction donnée.
     * Les calculs intègrent les décalages spécifiques (notamment les sauts de 2 sur un axe) 
     * propres au système de coordonnées doublées.
     *
     * @param mode      Le mode d'orientation de la grille (POINTY ou FLAT).
     * @param direction La direction vers laquelle se déplacer.
     * @return Une nouvelle instance de {@link CoordinateDoubled} représentant la case voisine.
     * @throws InvalidParameterException Si la direction fournie n'est pas valide pour le mode donné.
     */
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

    /**
     * Compare cette coordonnée doublée avec un autre objet pour vérifier leur égalité.
     *
     * @param obj L'objet à comparer.
     * @return {@code true} si l'objet est une {@link CoordinateDoubled} avec les mêmes 
     *         valeurs de x et de y, {@code false} sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CoordinateDoubled other)) return false;
        return y == other.y && x == other.x;
    }

    /**
     * Génère un code de hachage pour cette coordonnée basé sur ses composantes y et x.
     *
     * @return Le code de hachage généré.
     */
    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    /**
     * Retourne une représentation textuelle de la coordonnée doublée.
     * Le format généré est de la forme "[y,x]".
     *
     * @return La chaîne de caractères représentant la coordonnée.
     */
    @Override
    public String toString() {
        return "[" + y + "," + x + "]";
    }
}