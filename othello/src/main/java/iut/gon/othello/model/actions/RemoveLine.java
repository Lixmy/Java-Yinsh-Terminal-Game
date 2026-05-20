package iut.gon.othello.model.actions;

import java.util.Set;

import iut.gon.hexagonalcoordinates.Coordinate;

/**
 * Représente une action consistant à retirer une ligne (un alignement de jetons)
 * sur le plateau de jeu, ainsi qu'à sélectionner ou retirer un anneau associé à cette action.
 */
public class RemoveLine extends Action {
    private Set<Coordinate> line;
    private Coordinate ring;
    
    /**
     * Construit une nouvelle action de suppression de ligne.
     *
     * @param line L'ensemble des coordonnées formant la ligne à retirer.
     * @param ring La coordonnée de l'anneau impliqué dans cette action.
     */
    public RemoveLine(Set<Coordinate> line, Coordinate ring) {
        this.line = line;
        this.ring = ring;
    }
    
    public Set<Coordinate> getLine() {
        return line;
    }

    public void setLine(Set<Coordinate> line) {
        this.line = line;
    }
    
    public Coordinate getRing() {
        return ring;
    }

    public void setRing(Coordinate ring) {
        this.ring = ring;
    }
    
}