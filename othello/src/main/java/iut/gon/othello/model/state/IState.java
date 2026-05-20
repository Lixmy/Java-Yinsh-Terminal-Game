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

public interface IState {
	
	IState move(Move move);
	
	IState removeLine(RemoveLine rm);
	
	Set<Coordinate> availableMoves(Coordinate from);
	
	Map<Coordinate, Token> board();
	
	Map<Team, List<Coordinate>> rings();
	
	List<Set<Coordinate>> lines();
	
	Team turn();
	
	Team winner();
	
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
	
	IState removeToken(Coordinate c);
	
	IState toggleToken(Coordinate position, Team team, Class<?> token);
}