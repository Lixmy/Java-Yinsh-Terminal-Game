package iut.gon.othello.model.factory;

import iut.gon.othello.model.state.IState;

public interface IFactory {
    IState testState();
    IState stateForBlackLineTest();
    IState stateForWhiteLineTest();
    IState emptyState();
    IState doubleLineStateTest();
}