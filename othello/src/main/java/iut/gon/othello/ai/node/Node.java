package iut.gon.othello.ai.node;

import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;

public class Node {

    private final IState state;
    private final Node parent;
    private final Action action;

    public Node(IState state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }

    public IState getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Action getAction() {
        return action;
    }
}