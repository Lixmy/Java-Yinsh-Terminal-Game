package iut.gon.othello.model.state;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.hexagonalcoordinates.Point;
import iut.gon.othello.model.*;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.tokens.Token;

/**
 * Interface définissant l'état immuable d'une partie de jeu.
 */
public interface IState {
	
	/**
	 * Applique une action de déplacement sur le plateau.
	 *
	 * @param move L'action de déplacement à effectuer.
	 * @return Un nouvel état représentant le jeu après le déplacement.
	 */
	IState move(Move move);
	
	/**
	 * Applique une action de suppression de ligne.
	 *
	 * @param rm L'action de retrait de ligne à effectuer.
	 * @return Un nouvel état représentant le jeu après la suppression.
	 */
	IState removeLine(RemoveLine rm);
	
	/**
	 * Détermine l'ensemble des destinations valides pour un déplacement.
	 *
	 * @param from La coordonnée de départ.
	 * @return Un ensemble contenant toutes les coordonnées d'arrivée légales.
	 */
	Set<Coordinate> availableMoves(Coordinate from);
	
	/**
	 * Récupère la représentation actuelle du plateau de jeu.
	 *
	 * @return Une map associant chaque coordonnée à son jeton (ou null).
	 */
	Map<Coordinate, Token> board();
	
	/**
	 * Récupère les positions des anneaux pour chaque équipe.
	 *
	 * @return Une map associant chaque équipe à la liste de ses anneaux.
	 */
	Map<Team, List<Coordinate>> rings();
	
	/**
	 * Récupère la liste des lignes (alignements de pions) actuellement formées.
	 *
	 * @return Une liste d'ensembles de coordonnées, représentant les lignes.
	 */
	List<Set<Coordinate>> lines();
	
	/**
	 * Indique de quelle équipe c'est le tour de jouer.
	 *
	 * @return L'équipe qui doit effectuer la prochaine action.
	 */
	Team turn();
	
	/**
	 * Vérifie si la partie est terminée et retourne l'équipe gagnante.
	 *
	 * @return L'équipe victorieuse, ou null si la partie est en cours.
	 */
	Team winner();
	
	/**
	 * Méthode utilitaire statique permettant de détecter les alignements 
	 * de 5 pions de la même couleur sur un plateau donné.
	 *
	 * @param board L'état du plateau de jeu sous forme de map.
	 * @return Une liste contenant les ensembles de coordonnées des lignes détectées.
	 */
	static List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> board) {
        List<Set<Coordinate>> allLines = new java.util.ArrayList<>();
        
        // Organisation des pions par équipe et par Point 2D
        Map<Team, Map<Point, Coordinate>> pawnsMap = new HashMap<>();
        for (Team t : Team.values()) {
            pawnsMap.put(t, new HashMap<>());
        }
        
        for (Map.Entry<Coordinate, Token> entry : board.entrySet()) {
            Coordinate coord = entry.getKey();
            Token token = entry.getValue();
            
            if (token instanceof iut.gon.othello.model.tokens.Pawn) {
                pawnsMap.get(token.getTeam()).put(coord.to2DCoordinate(), coord);
            }
        }
        
        // Les 3 directions de l'axe dans le système Doubled (car on utilise .to2DCoordinate)
        int[][] directions = {
            {2, 0},  // Est
            {1, 1},  // Sud-Est
            {-1, 1}  // Sud-Ouest
        };
        
        // Recherche des alignements de 5 pions
        for (Team team : Team.values()) {
            Map<Point, Coordinate> teamPawns = pawnsMap.get(team);
            
            for (Map.Entry<Point, Coordinate> entry : teamPawns.entrySet()) {
                Point startPoint = entry.getKey();
                Coordinate startCoord = entry.getValue();
                
                for (int[] dir : directions) {
                    int dx = dir[0];
                    int dy = dir[1];
                    
                    Set<Coordinate> currentLine = new HashSet<>();
                    currentLine.add(startCoord);
                    boolean isLine = true;
                    
                    for (int k = 1; k < 5; k++) {
                        Point nextPoint = 
                            new Point(startPoint.x() + k * dx, startPoint.y() + k * dy);
                        
                        if (teamPawns.containsKey(nextPoint)) {
							currentLine.add(teamPawns.get(nextPoint));
                        } else {
                            isLine = false;
                            break;
                        }
                    }
                    
                    if (isLine) {
                        allLines.add(currentLine);
                    }
                }
            }
        }
        
        return allLines;
    }
	
	/**
	 * Supprime le jeton présent à la coordonnée spécifiée.
	 *
	 * @param c La coordonnée de la case à vider.
	 * @return Le nouvel état après suppression du jeton.
	 */
	IState removeToken(Coordinate c);
	
	/**
	 * Modifie, remplace ou ajoute un jeton à une position donnée.
	 *
	 * @param position La coordonnée cible.
	 * @param team     L'équipe du nouveau jeton.
	 * @param token    La classe représentant le type de jeton à placer.
	 * @return Le nouvel état intégrant le changement.
	 */
	IState toggleToken(Coordinate position, Team team, Class<?> token);
}