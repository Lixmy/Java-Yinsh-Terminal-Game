package iut.gon.othello.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import iut.gon.hexagonalcoordinates.Coordinate;
import iut.gon.hexagonalcoordinates.CoordinateCube;
import iut.gon.hexagonalcoordinates.CoordinateDoubled;
import iut.gon.hexagonalcoordinates.Point;
import iut.gon.othello.model.factory.FactoryCube;
import iut.gon.othello.model.factory.FactoryDoubled;
import iut.gon.othello.model.factory.IFactory;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

public class CUIMain {

	private static int mode;
	
	public static void affiche(IState state) {
	    int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
	    int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

	    for (Coordinate c : state.board().keySet()) {
	        Point p = c.to2DCoordinate();
	        if (p.x() < minX) minX = p.x();
	        if (p.y() < minY) minY = p.y();
	        if (p.x() > maxX) maxX = p.x();
	        if (p.y() > maxY) maxY = p.y();
	    }

	    int width  = maxX - minX + 1;
	    int height = maxY - minY + 1;
	    String[][] grid = new String[height][width];
	    for (String[] row : grid) Arrays.fill(row, " ");

	    for (Map.Entry<Coordinate, Token> entry : state.board().entrySet()) {
	        Point p = entry.getKey().to2DCoordinate();
	        Token token = entry.getValue();
	        int col = p.x() - minX;
	        int row = p.y() - minY;

	        if (token == null) {
	            grid[row][col] = "_";
	        } else if (token instanceof Ring && token.getTeam() == Team.WHITE) {
	            grid[row][col] = "o";
	        } else if (token instanceof Ring && token.getTeam() == Team.BLACK) {
	            grid[row][col] = "O";
	        } else if (token instanceof Pawn && token.getTeam() == Team.WHITE) {
	            grid[row][col] = ".";
	        } else if (token instanceof Pawn && token.getTeam() == Team.BLACK) {
	            grid[row][col] = "x";
	        }
	    }

	    for (String[] r : grid) {
	        boolean vide = true;
	        for (String cell : r) {
	            if (!cell.equals(" ")) vide = false;
	        }
	        if (!vide) {
	            for (String cell : r) System.out.print(cell);
	            System.out.println();
	        }
	    }
	}

	private static Coordinate lireCoordonnee(Scanner scanner, IState state) {
	    Coordinate c = null;
	    while (c == null) {
	        try {
	        	if(mode == 1) {
	        		System.out.print("Entrez une coordonnée (q r s) : ");
	        		int q = scanner.nextInt();
		            int r = scanner.nextInt();
		            int s = scanner.nextInt();
		            Coordinate temp = new CoordinateCube(q, r, s);
		            if (!state.board().containsKey(temp)) {
		                System.out.println("Coordonnée hors du terrain, réessayez");
		            } else {
		                c = temp;
		            }
		        } else {
		        	System.out.println("ENtrez une coordonnée (y x) : ");
		        	int y = scanner.nextInt();
		        	int x = scanner.nextInt();
		        	Coordinate temp = new CoordinateDoubled(y, x);
		        	if(!state.board().containsKey(temp)) {
		        		System.out.println("Coordonnée hors du terrain, réessayez");
		        	} else {
		        		c = temp;
		        	}
		        }
	        } catch (Exception e) {
	        	System.out.println("Format invalide, réessayez");
	            scanner.nextLine();
	        }      
	    }
	    return c;
	}

	private static IState initialiserPartie(IFactory factory) {
		
		IState state = factory.emptyState();
		List<Coordinate> allCoords = new ArrayList<>(state.board().keySet());

		Random random = new Random();
		for (int i = allCoords.size() - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			Coordinate temp = allCoords.get(i);
			allCoords.set(i, allCoords.get(j));
			allCoords.set(j, temp);
		}

		for (int i = 0; i < 5; i++) {
			state = state.toggleToken(allCoords.get(i), Team.WHITE, Ring.class);
			state = state.toggleToken(allCoords.get(i + 5), Team.BLACK, Ring.class);
		}
		return state;
	}

	private static void jouerDeplacement(Scanner scanner, Model model) {
		boolean coupJoue = false;
		while (!coupJoue) {
			System.out.println("Vos anneaux sont en : " + model.getRings(model.getTurn()));
			System.out.println("Choisissez l'anneau à déplacer :");
			Coordinate from = lireCoordonnee(scanner, model.getCurrentState());

			Set<Coordinate> moves = model.movesFrom(from);
			if (moves.isEmpty()) {
				System.out.println("Cet anneau ne peut pas bouger, choisir un autre");
				continue;
			}

			System.out.println("Déplacements possibles : " + moves);
			System.out.println("Choisissez la destination :");
			Coordinate to = lireCoordonnee(scanner, model.getCurrentState());

			try {
				model.moveRing(from, to);
				coupJoue = true;
			} catch (Exception e) {
				System.out.println("Coup invalide : " + e.getMessage());
			}
		}
	}

	private static void jouerSuppression(Scanner scanner, Model model) {
		while (!model.getPawnsLines().isEmpty()) {
			List<Set<Coordinate>> lignes = model.getPawnsLines();
			System.out.println("Lignes à supprimer : ");
			int i = 1;
			for (Set<Coordinate> ligne : lignes) {
				System.out.println("  Ligne " + i++ + " : " + ligne);
			}

			System.out.println("Entrez les 5 coordonnées de la ligne à supprimer (q r s) x5 :");
			Set<Coordinate> ligneChoisie = new HashSet<>();
			for (int k = 0; k < 5; k++) {
				ligneChoisie.add(lireCoordonnee(scanner, model.getCurrentState()));
			}

			System.out.println("Entrez la coordonnée de l'anneau à retirer :");
			Coordinate anneau = lireCoordonnee(scanner, model.getCurrentState());

			try {
				model.removeLine(ligneChoisie, anneau);
			} catch (Exception e) {
				System.out.println("Suppression invalide : " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		IState etatInitial;
		
		
		System.out.println("=== OTHELLO ===");
		System.out.println("Choissisez l'affichage : 1 - FLAT (Coordonées 3D)  2 - POINTY (Coordonnées 4D");
		System.out.println("o=anneau blanc  O=anneau noir  .=pion blanc  x=pion noir  _=vide");

		
		while (mode != 1 && mode != 2) {
			System.out.println(": ");
			try {
				mode = scanner.nextInt();
			} catch (Exception e) {
				scanner.nextLine();
			}
		}

		if (mode == 1) {
			etatInitial = initialiserPartie(new FactoryCube());
		} else {
			etatInitial = initialiserPartie(new FactoryDoubled());
		}
		Model model = new Model(etatInitial);
		while (model.getCurrentState().winner() == null) {
			affiche(model.getCurrentState());
			System.out.println("Tour de : " + model.getTurn());
			if (!model.getPawnsLines().isEmpty()) {
				System.out.println("Une ligne de 5 pions existe, il faut la supprimer");
				jouerSuppression(scanner, model);
			} else {
				jouerDeplacement(scanner, model);
				if (!model.getPawnsLines().isEmpty()) {
					System.out.println("Vous avez créé une ligne !");
					jouerSuppression(scanner, model);
				}
			}
		}

		affiche(model.getCurrentState());
		System.out.println("=== FIN ===");
		System.out.println("Gagnant : " + model.getCurrentState().winner());
		scanner.close();
	}

}