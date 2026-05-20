package iut.gon.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Token;

/**
 * Classe Model représentant le gestionnaire central de la partie.
 * Elle sert de pont (façade) entre l'interface utilisateur ou l'IA et l'état immuable du jeu (IState).
 * Elle conserve l'état courant et le met à jour à chaque nouvelle action effectuée.
 */
public class Model {
    private IState currentState;

    /**
     * Construit un nouveau modèle gérant la partie à partir d'un état initial.
     *
     * @param currentState L'état initial du jeu ({@link IState}).
     */
    public Model(IState currentState) {
        super();
        this.currentState = currentState;
    }

    public IState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IState currentState) {
        this.currentState = currentState;
    }

    /**
     * Récupère l'ensemble des déplacements légaux possibles pour un anneau situé à une coordonnée donnée.
     *
     * @param from La coordonnée de départ de l'anneau.
     * @return Un ensemble ({@link Set}) des coordonnées d'arrivée légales.
     */
    public Set<Coordinate> movesFrom(Coordinate from) {
        return currentState.availableMoves(from);
    }
    
    /**
     * Applique un déplacement d'anneau sur le plateau et met à jour l'état du modèle.
     *
     * @param from La coordonnée de départ de l'anneau.
     * @param to   La coordonnée d'arrivée de l'anneau.
     */
    public void moveRing(Coordinate from, Coordinate to) {
        Move m = new Move(from, to);
        currentState = currentState.move(m);
    }
    
    public List<Set<Coordinate>> getPawnsLines() {
        return currentState.lines();
    }
    
    /**
     * Retire une ligne complétée ainsi que l'anneau associé, puis met à jour l'état du modèle.
     *
     * @param line L'ensemble des coordonnées de la ligne de pions à supprimer.
     * @param ring La coordonnée de l'anneau à retirer du jeu.
     */
    public void removeLine(Set<Coordinate> line, Coordinate ring) {
        RemoveLine r = new RemoveLine(line, ring);
        currentState = currentState.removeLine(r);
    }
    
    public Map<Coordinate, Token> getBoard() {
        return currentState.board();
    }
    
    public Token getTokenAt(Coordinate c) {
        return currentState.board().get(c);
    }
    
    /**
     * Vérifie si une coordonnée appartient au plateau de jeu courant.
     *
     * @param c La coordonnée à vérifier.
     * @return {@code true} si la case fait partie du plateau, {@code false} sinon.
     */
    public boolean isInField(Coordinate c) {
        return currentState.board().containsKey(c);
    }
    
    public List<Coordinate> getRings(Team team) {
        return currentState.rings().get(team);
    }
    
    public List<Coordinate> getPawn(Team team) {
        List<Coordinate> result = new ArrayList<>();
        for (Map.Entry<Coordinate, Token> entry : currentState.board().entrySet()) {
            Token t = entry.getValue();
            if (t instanceof Pawn && t.getTeam() == team) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
    
    public Team getTurn() {
        return currentState.turn();
    }
    
    /**
     * Supprime le jeton présent à une coordonnée donnée et met à jour l'état du modèle.
     *
     * @param c La coordonnée de la case à vider.
     */
    public void removeToken(Coordinate c) {
        currentState = currentState.removeToken(c);
    }
    
    /**
     * Ajoute, remplace ou supprime dynamiquement un jeton à une coordonnée, 
     * puis met à jour l'état courant. Un appel sur un jeton identique existant le supprime.
     *
     * @param position La coordonnée cible.
     * @param team     L'équipe propriétaire du jeton.
     * @param token    La classe représentant le type de jeton (ex: Pawn.class, Ring.class).
     */
    public void toggleToken(Coordinate position, Team team, Class<?> token) {
        currentState = currentState.toggleToken(position, team, token);
    }
    
}