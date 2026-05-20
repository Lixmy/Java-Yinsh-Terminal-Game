package iut.gon.othello.model.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.hexagonalcoordinates.CoordinateCube;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

/**
 * Implémentation d'une fabrique (Factory) dédiée à la création d'états de jeu (IState) 
 * utilisant le système de coordonnées cubiques (CoordinateCube).
 * Cette classe fournit plusieurs méthodes pour générer des configurations de plateau 
 * spécifiques, particulièrement utiles pour les tests unitaires et la vérification des règles 
 * (détection de lignes, croisements, etc.).
 */
public class FactoryCube implements IFactory {

    /**
     * Génère un état de jeu de test standard avec une disposition prédéfinie 
     * d'anneaux et de pions pour les équipes noires et blanches.
     * C'est au tour de l'équipe noire de jouer.
     *
     * @return Un nouvel {@link IState} configuré pour des tests généraux.
     */
    @Override
    public IState testState() {
        IState empty = this.emptyState();
        Map<Coordinate, Token> board = new HashMap<>(empty.board());
        
        board.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
        board.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
        board.put(new CoordinateCube(2, 0, -2), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
        
        board.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
        board.put(new CoordinateCube(4, 0, -4), new Ring(Team.WHITE));
        board.put(new CoordinateCube(3, -2, -1), new Ring(Team.WHITE));
        board.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
        board.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
        
        board.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-4, 1, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(3, -4, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-5, 4, 1), new Pawn(Team.BLACK));
        
        board.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(-3, 3, 0), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));
        
        return new State(board, Team.BLACK, new ArrayList<>());
    }

    /**
     * Génère un état de jeu spécifiquement conçu pour tester la détection 
     * et la suppression de lignes pour l'équipe noire. Le plateau est fortement 
     * peuplé de pions noirs alignés.
     * C'est au tour de l'équipe noire de jouer.
     *
     * @return Un nouvel {@link IState} orienté pour les tests de lignes noires.
     */
    @Override
    public IState stateForBlackLineTest() {
        IState empty = this.emptyState();
        Map<Coordinate, Token> board = new HashMap<>(empty.board());
        
        board.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
        board.put(new CoordinateCube(2, 0, -2), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
        
        board.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
        board.put(new CoordinateCube(4, 0, -4), new Ring(Team.WHITE));
        board.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
        board.put(new CoordinateCube(3,-2, -1), new Ring(Team.WHITE));
        board.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
        
        board.put(new CoordinateCube(0, -1, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, -3, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, -4, 4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -3, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -4, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -5, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(3, -4, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(3, -1, -2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(4, -1, -3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(5, -1, -4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(3, 1, -4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, 2, -2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, 3, -3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-1, 3, -2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(1, 3, -4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, 3, -5), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-1, 5, -4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-2, 1, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-3, 1, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-4, 1, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-5, 1, 4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-5, 2, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-5, 3, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-5, 4, 1), new Pawn(Team.BLACK));
        
        board.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(-3, 3, 0), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));
        
        return new State(board, Team.BLACK, new ArrayList<>());
    }

    /**
     * Génère un état de jeu conçu pour tester la détection et la suppression 
     * de lignes pour l'équipe blanche. Il calcule explicitement les lignes formées 
     * lors de l'instanciation de l'état.
     * C'est au tour de l'équipe blanche de jouer.
     *
     * @return Un nouvel {@link IState} orienté pour les tests de lignes blanches.
     */
    @Override
    public IState stateForWhiteLineTest() {
        IState empty = this.emptyState();
        Map<Coordinate, Token> board = new HashMap<>(empty.board());
        
        board.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
        board.put(new CoordinateCube(2, 0, -2), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
        
        board.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
        board.put(new CoordinateCube(4, 0, -4), new Ring(Team.WHITE));
        board.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
        board.put(new CoordinateCube(3,-2, -1), new Ring(Team.WHITE));
        board.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
        
        board.put(new CoordinateCube(0, 0, 0), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-1, 1, 0), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, -1, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, -3, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, -4, 4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -3, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -4, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -5, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(3, -4, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(3, -1, -2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(4, -1, -3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(5, -1, -4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(3, 1, -4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, 2, -2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(0, 3, -3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-1, 3, -2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(1, 3, -4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, 3, -5), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-1, 5, -4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-2, 1, 1), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-3, 1, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-4, 1, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-5, 1, 4), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-5, 2, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-5, 3, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-5, 4, 1), new Pawn(Team.BLACK));
        
        board.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(-3, 3, 0), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));
        
        return new State(board, Team.WHITE, IState.getPawnsLines(board));
    }

    /**
     * Génère le plateau de jeu hexagonal vide initial.
     * La grille est construite avec un rayon de 5, en ignorant les coordonnées aux extrémités 
     * pour donner au plateau sa forme caractéristique tronquée.
     * C'est au tour de l'équipe noire de jouer.
     *
     * @return Un nouvel {@link IState} représentant un plateau vierge (valeurs à null).
     */
    @Override
    public IState emptyState() {
        Map<Coordinate, Token> board = new HashMap<>();

        int radius = 5;
        for (int q = -radius; q <= radius; q++) {
            for (int r = Math.max(-radius, -q - radius); r <= Math.min(radius, -q + radius); r++) {
                int s = -q - r;
                int absCount = 0;
                if (Math.abs(q) == radius) absCount++;
                if (Math.abs(r) == radius) absCount++;
                if (Math.abs(s) == radius) absCount++;
                if (absCount >= 2) continue;

                board.put(new CoordinateCube(q, r, s), null);
            }
        }

        return new State(board, Team.BLACK, new ArrayList<>());
    }

    /**
     * Génère un état de jeu spécifiquement conçu pour tester le comportement 
     * lors de l'apparition de lignes doubles (plusieurs lignes formées simultanément ou imbriquées).
     * C'est au tour de l'équipe noire de jouer.
     *
     * @return Un nouvel {@link IState} orienté pour les tests de lignes doubles.
     */
    @Override
    public IState doubleLineStateTest() {
        IState empty = this.emptyState();
        Map<Coordinate, Token> board = new HashMap<>(empty.board());
        
        board.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
        board.put(new CoordinateCube(2, 0, -2), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
        board.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
        
        board.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
        board.put(new CoordinateCube(4, 0, -4), new Ring(Team.WHITE));
        board.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
        board.put(new CoordinateCube(5, -3, -2), new Ring(Team.WHITE));
        board.put(new CoordinateCube(3, -3, 0), new Ring(Team.WHITE));
        
        board.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(-1, -2, 3), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(1, -1, 0), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
        board.put(new CoordinateCube(3, -2, -1), new Pawn(Team.BLACK));
        
        board.put(new CoordinateCube(0, -1, 1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(2, -1, -1), new Pawn(Team.WHITE));
        board.put(new CoordinateCube(3, -1, -2), new Pawn(Team.WHITE));
        
        return new State(board, Team.BLACK, new ArrayList<>());
    }
    
}