package cz.vse.adventura;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {

    public TextArea vystup;
    @FXML
    private TextField vstup;

    @FXML
    public void odesliVstup(ActionEvent actionEvent) {
        vystup.appendText(vstup.getText() + "\n");
        vstup.clear();
    }
}
