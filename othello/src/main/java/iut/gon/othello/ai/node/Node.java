package iut.gon.othello.ai.node;

import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;

/**
 * Représente un nœud dans l'arbre de recherche de l'intelligence artificielle.
 * Un nœud encapsule un état de jeu, une référence vers son nœud parent, 
 * ainsi que l'action qui a permis d'atteindre cet état depuis le parent.
 */
public class Node {

    private final IState state;
    private final Node parent;
    private final Action action;

    /**
     * Construit un nouveau nœud pour l'arbre de recherche.
     *
     * @param state  L'état de jeu associé à ce nœud.
     * @param parent Le nœud parent à partir duquel cet état a été généré (peut être {@code null} pour le nœud racine).
     * @param action L'action effectuée depuis le parent pour atteindre cet état (peut être {@code null} pour le nœud racine).
     */
    public Node(IState state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }

    /**
     * Récupère l'état de jeu encapsulé dans ce nœud.
     *
     * @return L'état de jeu {@link IState} actuel du nœud.
     */
    public IState getState() {
        return state;
    }

    /**
     * Récupère le nœud parent dans l'arbre de recherche.
     *
     * @return Le nœud {@link Node} parent, ou {@code null} s'il s'agit du nœud racine.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Récupère l'action qui a mené à cet état depuis le nœud parent.
     *
     * @return L'{@link Action} effectuée, ou {@code null} s'il s'agit du nœud racine.
     */
    public Action getAction() {
        return action;
    }
}