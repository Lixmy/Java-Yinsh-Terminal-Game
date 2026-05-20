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
import iut.gon.othello.ai.MinimaxAI;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.factory.FactoryCube;
import iut.gon.othello.model.factory.FactoryDoubled;
import iut.gon.othello.model.factory.IFactory;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

/**
 * Classe principale gérant l'interface en ligne de commande (CUI) du jeu Othello/Yinsh.
 * Elle contient la boucle principale, l'affichage dans la console, la lecture des entrées 
 * utilisateur et la gestion des tours (Humain vs Humain ou Humain vs IA).
 */
public class CUIMain {

    private static int mode;

    /**
     * Affiche l'état actuel du plateau de jeu dans la console.
     * Représente visuellement la grille hexagonale et les jetons à l'aide de caractères ASCII 
     * (o, O pour les anneaux ; ., x pour les pions).
     *
     * @param state L'état du jeu ({@link IState}) courant à afficher.
     */
    public static void affiche(IState state) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (Coordinate c : state.board().keySet()) {
            Point p = c.to2DCoordinate();
            if (p.x() < minX)
                minX = p.x();
            if (p.y() < minY)
                minY = p.y();
            if (p.x() > maxX)
                maxX = p.x();
            if (p.y() > maxY)
                maxY = p.y();
        }

        int width = maxX - minX + 1;
        int height = maxY - minY + 1;
        String[][] grid = new String[height][width];
        for (String[] row : grid)
            Arrays.fill(row, " ");

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
                if (!cell.equals(" "))
                    vide = false;
            }
            if (!vide) {
                for (String cell : r)
                    System.out.print(cell);
                System.out.println();
            }
        }
    }

    /**
     * Lit et vérifie les coordonnées entrées par l'utilisateur via la console.
     * Adapte la lecture en fonction du mode de coordonnées choisi (Cube ou Doubled) 
     * et boucle jusqu'à l'obtention d'une coordonnée appartenant au plateau.
     *
     * @param scanner Le {@link Scanner} pour lire l'entrée standard.
     * @param state   L'état actuel du jeu pour valider l'existence de la case.
     * @return La {@link Coordinate} valide saisie par le joueur.
     */
    private static Coordinate lireCoordonnee(Scanner scanner, IState state) {
        Coordinate c = null;
        while (c == null) {
            try {
                if (mode == 1) {
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
                    if (!state.board().containsKey(temp)) {
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

    /**
     * Initialise une nouvelle partie en générant un plateau vide et en y plaçant 
     * de manière aléatoire 5 anneaux blancs et 5 anneaux noirs.
     *
     * @param factory La fabrique ({@link IFactory}) à utiliser pour générer le plateau.
     * @return L'état de jeu initial ({@link IState}) avec les anneaux placés.
     */
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

    /**
     * Gère le tour de jeu d'un joueur humain devant effectuer un déplacement.
     * Demande à l'utilisateur de sélectionner un anneau, affiche les destinations possibles, 
     * et applique le mouvement une fois validé.
     *
     * @param scanner Le {@link Scanner} pour lire les entrées de l'utilisateur.
     * @param model   Le {@link Model} gérant la partie en cours.
     */
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

    /**
     * Gère le tour d'un joueur humain devant supprimer une ligne complétée (5 pions).
     * Invite l'utilisateur à entrer manuellement les coordonnées des 5 pions formant 
     * la ligne, ainsi que la coordonnée de l'anneau à retirer du jeu.
     *
     * @param scanner Le {@link Scanner} pour lire les entrées de l'utilisateur.
     * @param model   Le {@link Model} gérant la partie en cours.
     */
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

    /**
     * Point d'entrée de l'application en ligne de commande.
     * Permet à l'utilisateur de configurer les paramètres de la partie (mode IA ou Joueur,
     * système de coordonnées) et lance la boucle de jeu principale.
     *
     * @param args Les arguments passés à l'exécution du programme.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IState etatInitial;

        System.out.println("=== OTHELLO ===");

        System.out.println("Choissisez le mode de jeu : 1 - JvJ  2 - JvE");

        int choix = 0;
        while (choix != 1 && choix != 2) {
            System.out.println(": ");
            try {
                choix = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
            }
        }

        System.out.println("o=anneau blanc  O=anneau noir  .=pion blanc  x=pion noir  _=vide");
        System.out.println("Choisissez l'affichage : 1 - FLAT  2 - POINTY");
        while (mode != 1 && mode != 2) {
            System.out.print(": ");
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

        MinimaxAI ai = (choix == 2) ? new MinimaxAI(Team.BLACK, 3) : null;

        while (model.getCurrentState().winner() == null) {
            affiche(model.getCurrentState());
            System.out.println("Tour de : " + model.getTurn());

            boolean aucunMouvement = true;
            for (Coordinate ring : model.getRings(model.getTurn())) {
                if (!model.movesFrom(ring).isEmpty()) {
                    aucunMouvement = false;
                    break;
                }
            }
            if (aucunMouvement) {
                System.out.println("=== MATCH NUL ===");
                break;
            }

            if (choix == 2 && model.getTurn() == Team.BLACK) {
                System.out.println("L'IA réfléchit...");
                Action action = ai.chooseMove(model.getCurrentState());
                if (action instanceof Move m) {
                    model.moveRing(m.getFrom(), m.getTo());
                } else if (action instanceof RemoveLine rl) {
                    model.removeLine(rl.getLine(), rl.getRing());
                }
                
                while (!model.getPawnsLines().isEmpty() && model.getTurn() == Team.BLACK) {
                    Action removeAction = ai.chooseMove(model.getCurrentState());
                    if (removeAction instanceof RemoveLine rl) {
                        model.removeLine(rl.getLine(), rl.getRing());
                    }
                }
            }
            
            else {
                if (!model.getPawnsLines().isEmpty()) {
                    System.out.println("Une ligne existe, il faut la supprimer !");
                    jouerSuppression(scanner, model);
                } else {
                    jouerDeplacement(scanner, model);
                    if (!model.getPawnsLines().isEmpty()) {
                        System.out.println("Vous avez créé une ligne !");
                        jouerSuppression(scanner, model);
                    }
                }
            }
        }

        affiche(model.getCurrentState());
        System.out.println("=== FIN ===");
        System.out.println("Gagnant : " + model.getCurrentState().winner());
        scanner.close();
    }
}