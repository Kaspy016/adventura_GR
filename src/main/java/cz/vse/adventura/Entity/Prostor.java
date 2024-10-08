package cz.vse.adventura.Entity;

import cz.vse.adventura.Entity.Veci.Klic;
import cz.vse.adventura.Entity.Veci.Lano;
import cz.vse.adventura.Entity.Veci.Loucen;
import cz.vse.adventura.Entity.Veci.Vec;

import java.util.*;

public class Prostor {

    private boolean zamceno;
    private boolean potrebnaLoucen;
    private boolean temna;
    private boolean vysoko;
    private String nazev;
    private String popis;
    private String heslo;
    private int zlataky;
    private Batoh batoh;
    private Set<Prostor> vychody;
    private Set<Vec> veci = new HashSet<>();
    private Set<Klic> potrebneKlice;
    Mesec mesec;



    public Prostor(String nazev, String popis, String heslo) {
        this.nazev = nazev;
        this.popis = popis;
        this.heslo = heslo;
        zlataky = 0;
        zamceno = false;
        potrebnaLoucen = false;
        temna = false;
        potrebneKlice = new HashSet<>();
        vychody = new HashSet<>();
        veci = new HashSet<>();
    }


    // Přidá východ z aktuálního prostoru
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    // Přidá věc do prostoru
    public boolean pridejVec (Vec vec) {
        for (Vec pritomnaVec: veci) {
            if (pritomnaVec == vec) {
                return false;
            }
        }
        return veci.add(vec);
    }

    // Přečte věc z prostoru
    public Vec prectiVec(String nazevVeci) {
        Vec nalezenaVec = null;
        for (Vec vec : veci) {
            if (vec.getNazev().equals(nazevVeci)) {
                nalezenaVec = vec;
                break;
            }
        }
        if (nalezenaVec != null) {
            veci.remove(nalezenaVec);
        }
        return nalezenaVec;
    }

    // Vrátí popis věcí v prostoru
    public String popisVeciVProstoru() {
        if (veci.isEmpty()) {
            return "Prostor je prázdný.";
        } else {
            // Převést věci do seznamu
            List<Vec> veciSeznam = new ArrayList<>(veci);
            // Seřadit věci abecedně
            veciSeznam.sort(Comparator.comparing(Vec::getNazev));

            StringBuilder obsahProstoru = new StringBuilder("Obsah prostoru:");
            for (int i = 0; i < veciSeznam.size(); i++) {
                obsahProstoru.append(" ").append(veciSeznam.get(i).getNazev());
                if (i < veciSeznam.size() - 1) {
                    obsahProstoru.append(",");
                }
            }
            return obsahProstoru.toString();
        }
    }




    // Porovná prostor s jiným objektem
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prostor)) {
            return false;
        }
        Prostor druhy = (Prostor) o;
       return (java.util.Objects.equals(this.nazev, druhy.nazev));
    }


    // Vrátí hash kód prostoru
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }


    // Vrátí název prostoru
    public String getNazev() {
        return nazev;
    }

    // Vrátí dlouhý popis prostoru
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu();
    }

    public String popisVychodu() {
        List<String> nazvyVychodu = new ArrayList<>();
        for (Prostor sousedni : vychody) {
            nazvyVychodu.add(sousedni.getNazev());
        }
        Collections.sort(nazvyVychodu); // Seřadí názvy východů abecedně
        StringBuilder vracenyText = new StringBuilder("východy:");
        for (int i = 0; i < nazvyVychodu.size(); i++) {
            vracenyText.append(" ").append(nazvyVychodu.get(i));
            if (i < nazvyVychodu.size() - 1) {
                vracenyText.append(",");
            }
        }
        return vracenyText.toString();
    }



    // Vrátí sousední prostor podle zadaného názvu
    public Prostor vratSousedniProstor(String nazevSouseda) {
        if (batoh != null && jeZamceno()) {
            if (batoh.obsahujeKlic(getKlic())) {
                odemknout();
            } else {
                return null;
            }
        }
        return vychody.stream()
                .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                .findFirst()
                .orElse(null);
    }


// Vrátí kolekci východů z prostoru
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

// Přidá věc do prostoru na základě názvu
    public boolean pridejVec(String nazev) {
        return true;
    }

    // Najde věc v prostoru podle názvu
    public Vec najdiVec(String nazev) {
        for (Vec vec : veci) {
            if (vec.getNazev().equals(nazev)) {
                return vec;
            }
        }
        return null;
    }

    // Přidá zlaťáky do prostoru
    public void pridejZlataky(int pocetZlataku) {
        zlataky += pocetZlataku;
    }

    // Vrátí textovou informaci o zlaťácích v prostoru
    public String obsahZlataky() {
        if (zlataky == 0) {
            return "V místnosti nejsou žádné zlaťáky.";
        } else {
            return "V místnosti je " + zlataky + " zlaťáků.";
        }
    }

    // Vrátí počet zlaťáků v prostoru
    public int getZlataky() {
        // Náš prostor bude mít zlaťáky pouze pokud jsme je explicitně vytvořili při vytváření prostoru
        return zlataky;
    }

    public void setZlataky(int pocetZlataku) {
        this.zlataky = pocetZlataku;
    }


    // Vrátí informaci o zamčenosti prostoru
    public boolean jeZamceno() {
        return zamceno;
    }

    // Zamkne prostor
    public void zamknout() {
        zamceno = true;
    }

    // Odemkne prostor
    public void odemknout() {
        zamceno = false;
    }

    // Vrátí klíč přiřazený k místnosti
    public Klic getKlic() {
        // Vrací klíč přiřazený k místnosti
        if (potrebneKlice.isEmpty()) {
            return null;
        } else {
            return potrebneKlice.iterator().next();
        }
    }

    // Metoda pro přidání klíče k místnosti
    public void pridatKlicMistnosti(Klic klic) {
        potrebneKlice.add(klic);
    }

    // Vrátí informaci o temnotě v prostoru
    public boolean jeTemnota() {
        return (temna && !obsahujeLoucen());
    }


    private boolean obsahujeLoucen() {
        for (Vec vec : veci) {
            if (vec instanceof Loucen) {
                return true;
            }
        }
        return false;
    }

    // Nastaví temnotu v prostoru
    public void setTemna(boolean temna) {
        this.temna = temna;
    }


    // Lana
    private boolean obsahujeLano() {
        for (Vec vec : veci) {
            if (vec instanceof Lano) {
                return true;
            }
        }
        return false;
    }

    // Vrátí informaci o výšce v prostoru
    public boolean jeVysoko() {
        return (vysoko && !obsahujeLano());
    }

    // Nastaví výšku v prostoru
    public void setVysoko(boolean vysoko) {
        this.vysoko = vysoko;
    }

    // Vrátí heslo pro prostor
    public String getHeslo() {
        return heslo;
    }

    public boolean jeZaheslovano() {
        return !heslo.isEmpty();
    }

    public void odheslovat() {
        zamceno = false;
        heslo = "";
    }

}

