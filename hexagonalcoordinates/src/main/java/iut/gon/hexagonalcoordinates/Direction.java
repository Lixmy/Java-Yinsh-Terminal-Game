package iut.gon.hexagonalcoordinates;

/**
 * Énumération représentant les différentes directions de déplacement possibles 
 * dans une grille hexagonale.
 * Elle couvre l'ensemble des directions nécessaires pour la navigation, 
 * que la grille soit orientée en mode POINTY (pointes en haut/bas) 
 * ou en mode FLAT (bords plats en haut/bas).
 */
public enum Direction {
    NO,
    N,
    NE,
    E,
    SE,
    S,
    SO,
    O;

    /**
     * Calcule et retourne la direction diamétralement opposée à la direction courante.
     *
     * @return La {@link Direction} opposée.
     */
    public Direction opposite() {
        return switch (this) {
            case NO -> SE;
            case N -> S;
            case NE -> SO;
            case E -> O;
            case SE -> NO;
            case S -> N;
            case SO -> NE;
            case O -> E;
        };
    }
}