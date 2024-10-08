module cz.vse.adventura {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.adventura to javafx.fxml;
    exports cz.vse.adventura;
}