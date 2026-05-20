package iut.gon.othello.ai;

import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;

public interface Ai{

    Action chooseMove(IState state);

}