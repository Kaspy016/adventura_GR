package cz.vse.adventura.Prikazy;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.Entity.Veci.Vec;

public class PrikazVezmi implements IPrikaz {
    private static final String NAZEV = "vezmi";
    private HerniPlan plan;

    public PrikazVezmi(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám vzít?";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vec = aktualniProstor.prectiVec(nazevVeci);

        // Pokud v místnosti nebyla nalezena běžná věc, zkusíme hledat zlaťáky
        if (vec == null) {
            // Pokud je název "Zlaťáky", přidáme všechny zlaťáky z aktuální místnosti do měšce
            if (nazevVeci.equalsIgnoreCase("Zlaťáky")) {
                int pocetZlataku = aktualniProstor.getZlataky();
                if (pocetZlataku > 0) {
                    plan.getMesec().pridejZlataky(pocetZlataku);
                    aktualniProstor.setZlataky(0); // Nastavíme počet zlaťáků v místnosti na 0
                    return "Vzal(a) jsi všechny zlaťáky z této místnosti.";
                } else {
                    return "V této místnosti nejsou žádné zlaťáky k sebrání.";
                }
            } else {
                return "Taková věc tu není nebo ji nelze vzít.";
            }
        }

        // Pokud byla nalezena běžná věc, pokračujeme jako dříve
        if (plan.getBatoh().pridejVec(vec)) {
            return "Vzal(a) jsi " + nazevVeci;
        } else {
            aktualniProstor.pridejVec(vec); // vrátíme věc zpět, pokud se ji nepodaří umístit do batohu
            return "Tvůj batoh je plný.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
