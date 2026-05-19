module iut.gon.othello {
	requires iut.gon.hexagonalcoordinates;
	requires java.desktop;

    exports iut.gon.othello.model;
    exports iut.gon.othello.model.state;
    exports iut.gon.othello.model.actions;
    exports iut.gon.othello.model.tokens;
}