module iut.gon.hexagonalcoordinates {
    requires javafx.controls;
    requires javafx.fxml;

    opens iut.gon.hexagonalcoordinates to javafx.fxml;
    exports iut.gon.hexagonalcoordinates;
}
