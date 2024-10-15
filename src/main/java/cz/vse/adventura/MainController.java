package cz.vse.adventura;

import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.Entity.Veci.Vec;
import cz.vse.adventura.Prikazy.PrikazJdi;
import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.IHra;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MainController {

    @FXML
    private ImageView hrac;
    @FXML
    private ListView<Prostor> panelVychodu;
    @FXML
    private ListView<Vec> panelVeci;
    @FXML
    private ListView<Vec> panelProstoru;
    @FXML
    private ListView<String> panelZlataku;
    @FXML
    private TextArea vystup;
    @FXML
    private Button tlacitkoOdesli;
    @FXML
    private TextField vstup;
    private IHra hra = new Hra();

    private Label seznamZlatakuVProstoru;


    private ObservableList<Prostor> seznamVychodu = FXCollections.observableArrayList();
    private ObservableList<Vec> seznamVeciVBatohu = FXCollections.observableArrayList();
    private ObservableList<Vec> seznamVeciVProstoru = FXCollections.observableArrayList();



    private Map<String, Point2D> souradniceProstoru = new HashMap<>();

    @FXML
    private void initialize() {
        vystup.appendText(hra.vratUvitani()+"\n\n");
        vystup.setEditable(false);
        Platform.runLater(() -> vstup.requestFocus());
        panelVychodu.setItems(seznamVychodu);
        panelVeci.setItems(seznamVeciVBatohu);
        panelProstoru.setItems(seznamVeciVProstoru);
        hra.getHerniPlan().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
            aktualizujSeznamVychodu();
            aktualizujPolohuHrace();
        });
        hra.registruj(ZmenaHry.KONEC_HRY, () -> aktualizujKonecHry());
        aktualizujSeznamVychodu();
        aktualizujObsahBatohu();
        vlozSouradnice();
        panelVychodu.setCellFactory(param -> new ListCellProstor());
        panelVeci.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Vec vec, boolean empty) {
                super.updateItem(vec, empty);
                if (empty || vec == null) {
                    setText(null);
                } else {
                    setText(vec.getNazev());
                }
            }
        });

        panelProstoru.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Vec vec, boolean empty) {
                super.updateItem(vec, empty);
                if (empty || vec == null) {
                    setText(null);
                } else {
                    setText(vec.getNazev());
                }
            }
        });




    }

    private void vlozSouradnice() {
        souradniceProstoru.put("tvrz", new Point2D(354,399));
        souradniceProstoru.put("sklep", new Point2D(456,399));
        souradniceProstoru.put("zbrojnice", new Point2D(556,399));
        souradniceProstoru.put("sklad", new Point2D(631,322));
        souradniceProstoru.put("tajemná_místnost", new Point2D(457,307));
        souradniceProstoru.put("zahrada", new Point2D(285,322));
        souradniceProstoru.put("brána", new Point2D(207,250));
        souradniceProstoru.put("temná_chodba", new Point2D(354,250));
        souradniceProstoru.put("sál", new Point2D(354,149));
        souradniceProstoru.put("truhla", new Point2D(354,46));
        souradniceProstoru.put("věž", new Point2D(93,399));
        souradniceProstoru.put("trezor", new Point2D(93,297));
    }

    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
        aktualizujSeznamVeciVProstoru();
        aktualizujSeznamZlatakuVProstoru();
    }

    private void aktualizujObsahBatohu() {
        seznamVeciVBatohu.clear();
        seznamVeciVBatohu.addAll(hra.getHerniPlan().getBatoh().getVeci());
        System.out.println("Aktualizuji obsah batohu: ");
        hra.getHerniPlan().getBatoh().getVeci().forEach(vec -> System.out.println(vec.getNazev()));
    }

    private void aktualizujSeznamVeciVProstoru() {
        seznamVeciVProstoru.clear();
        seznamVeciVProstoru.addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
    }

    private void aktualizujSeznamZlatakuVProstoru() {
        panelZlataku.getItems().clear();
        int pocetZlataku = hra.getHerniPlan().getAktualniProstor().getZlataky();
        panelZlataku.getItems().add("Počet zlaťáků: " + pocetZlataku);
    }




    private void aktualizujPolohuHrace() {
        String prostor = hra.getHerniPlan().getAktualniProstor().getNazev();
        hrac.setLayoutX(souradniceProstoru.get(prostor).getX());
        hrac.setLayoutY(souradniceProstoru.get(prostor).getY());
    }

    private void aktualizujKonecHry() {
        System.out.println("aktualizuji konec");
        if(hra.konecHry()) {
            vystup.appendText("\n" + hra.vratEpilog());
        }
        vstup.setDisable(hra.konecHry());
        tlacitkoOdesli.setDisable(hra.konecHry());
        panelVychodu.setDisable(hra.konecHry());
    }

    @FXML
    private void odesliVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
    }

    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> "+ prikaz +"\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek+"\n\n");
        aktualizujObsahBatohu();

    }
    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Jste si jistí, že chcete ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @FXML
    private void klikPanelVychodu(MouseEvent mouseEvent) {
        Prostor cil = panelVychodu.getSelectionModel().getSelectedItem();
        if(cil==null) return;
        String prikaz = PrikazJdi.NAZEV+" " +cil.getNazev();
        System.out.println(prikaz);
        zpracujPrikaz(prikaz);


    }

    public void klikPanelVeci(MouseEvent mouseEvent) {

    }

    public void klikPanelProstoru(MouseEvent mouseEvent) {

    }

    public void klikPanelZlataku(MouseEvent mouseEvent) {
    }
}
