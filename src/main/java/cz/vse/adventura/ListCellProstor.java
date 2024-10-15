package cz.vse.adventura;

import cz.vse.adventura.Entity.Prostor;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ListCellProstor extends ListCell<Prostor> {
    @Override
    protected void updateItem(Prostor prostor, boolean empty) {
        super.updateItem(prostor, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(prostor.getNazev());
            String cesta = getClass().getResource("prostory/"+prostor.getNazev()+".jpg").toExternalForm();
            ImageView iw = new ImageView(cesta);
            iw.setFitWidth(120);
            iw.setPreserveRatio(true);
            setGraphic(iw);
        }
    }
}
