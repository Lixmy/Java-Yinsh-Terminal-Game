package iut.gon.hexagonalcoordinates;

/**
 * Représente un point dans un système de coordonnées cartésiennes 2D.
 * En tant que record, cette classe est immuable et encapsule simplement 
 * une position sur un axe horizontal (x) et vertical (y).
 *
 * @param x L'abscisse (coordonnée horizontale) du point.
 * @param y L'ordonnée (coordonnée verticale) du point.
 */
public record Point(int x, int y) {
    
    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}