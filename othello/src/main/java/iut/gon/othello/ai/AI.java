package iut.gon.othello.ai;

import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;

public interface AI{

    Action chooseMove(IState state);

}