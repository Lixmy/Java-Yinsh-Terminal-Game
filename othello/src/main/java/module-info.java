module iut.gon.othello {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires iut.gon.hexagonalcoordinates;

    opens iut.gon.othello to javafx.fxml;
    exports iut.gon.othello;
}
