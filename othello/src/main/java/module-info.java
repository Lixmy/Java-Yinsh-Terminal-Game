module iut.gon.othello {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens iut.gon.othello to javafx.fxml;
    exports iut.gon.othello;
}
