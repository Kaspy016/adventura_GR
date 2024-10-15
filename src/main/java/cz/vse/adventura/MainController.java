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

import java.util.Comparator;
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
        souradniceProstoru.put("tvrz", new Point2D(213,250)); //x y
        souradniceProstoru.put("sklep", new Point2D(282,250)); //x  y
        souradniceProstoru.put("zbrojnice", new Point2D(351,250)); //x y
        souradniceProstoru.put("sklad", new Point2D(402,197)); //x y
        souradniceProstoru.put("tajemná_místnost", new Point2D(282,186)); //x y
        souradniceProstoru.put("zahrada", new Point2D(161,197)); //x  y
        souradniceProstoru.put("brána", new Point2D(109,146)); //x y
        souradniceProstoru.put("temná_chodba", new Point2D(213,146)); //x y
        souradniceProstoru.put("sál", new Point2D(213,79)); //x y
        souradniceProstoru.put("truhla", new Point2D(213,8)); //x y
        souradniceProstoru.put("věž", new Point2D(31,250)); //x y
        souradniceProstoru.put("trezor", new Point2D(31,177)); //x y
    }

    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
        seznamVychodu.sort(Comparator.comparing(Prostor::getNazev));
        aktualizujSeznamVeciVProstoru();
        aktualizujSeznamZlatakuVProstoru();
    }

    private void aktualizujObsahBatohu() {
        seznamVeciVBatohu.clear();
        seznamVeciVBatohu.addAll(hra.getHerniPlan().getBatoh().getVeci());
        seznamVeciVBatohu.sort(Comparator.comparing(Vec::getNazev));
        System.out.println("Aktualizuji obsah batohu: ");
        hra.getHerniPlan().getBatoh().getVeci().forEach(vec -> System.out.println(vec.getNazev()));
    }

    private void aktualizujSeznamVeciVProstoru() {
        seznamVeciVProstoru.clear();
        seznamVeciVProstoru.addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
        seznamVeciVProstoru.sort(Comparator.comparing(Vec::getNazev));
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
        panelVeci.setDisable(hra.konecHry());
        panelProstoru.setDisable(hra.konecHry());
        panelZlataku.setDisable(hra.konecHry());
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

    @FXML
    public void klikPanelVeci(MouseEvent mouseEvent) {
        Vec vybranaVec = panelVeci.getSelectionModel().getSelectedItem();
        if (vybranaVec != null) {
            if (hra.getHerniPlan().getAktualniProstor().pridejVec(vybranaVec)) {
                hra.getHerniPlan().getBatoh().odeberVec(vybranaVec.getNazev());
                aktualizujObsahBatohu();
                aktualizujSeznamVeciVProstoru();
                vystup.appendText("Položil(a) jsi: " + vybranaVec.getNazev() + "\n");
            } else {
                vystup.appendText("Nemůžeš položit tuto věc zde.\n");
            }
        }
    }

    @FXML
    public void klikPanelProstoru(MouseEvent mouseEvent) {
        Vec vybranaVec = panelProstoru.getSelectionModel().getSelectedItem();
        if (vybranaVec != null) {
            if (!vybranaVec.isPrenositelna()) {
                vystup.appendText("Hádanku nemůžeš vzít. Přečti si ji a odpověz.\n");
                return;
            }

            // Pokusíme se přidat věc do batohu
            if (hra.getHerniPlan().getBatoh().pridejVec(vybranaVec)) {
                if (hra.getHerniPlan().getAktualniProstor().odeberVec(vybranaVec.getNazev())) {
                    aktualizujObsahBatohu();
                    aktualizujSeznamVeciVProstoru();
                    vystup.appendText("Sebral(a) jsi: " + vybranaVec.getNazev() + "\n");
                } else {
                    vystup.appendText("Nemohu odebrat tuto věc z prostoru.\n");
                }
            } else {
                vystup.appendText("Tvůj inventář je plný, nemůžeš vzít tuto věc.\n");
            }
        }
    }


    @FXML
    public void klikPanelZlataku(MouseEvent mouseEvent) {
        if (!panelZlataku.getItems().isEmpty()) {
            int pocetZlataku = hra.getHerniPlan().getAktualniProstor().getZlataky();
            if (pocetZlataku > 0) {
                hra.getHerniPlan().getMesec().pridejZlataky(pocetZlataku);
                hra.getHerniPlan().getAktualniProstor().setZlataky(0);
                aktualizujSeznamZlatakuVProstoru();
                vystup.appendText("Vzal(a) jsi všechny zlaťáky do měšce.\n");
            } else {
                vystup.appendText("V této místnosti nejsou žádné zlaťáky.\n");
            }
        }
    }
}
