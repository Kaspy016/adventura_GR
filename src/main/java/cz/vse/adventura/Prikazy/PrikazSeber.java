package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Batoh;
import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.Entity.Veci.Vec;
import cz.vse.adventura.logika.HerniPlan;

public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private HerniPlan plan;

    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }


    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (název věci k sebrání), vrátíme chybovou zprávu
            return "Co mám sebrat? Musíš zadat název věci.";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sebranaVec = aktualniProstor.prectiVec(nazevVeci);

        if (sebranaVec == null) {
            // pokud věc není v aktuálním prostoru, vrátíme odpovídající zprávu
            return "Taková věc tady není.";
        }

        Batoh batoh = plan.getBatoh();
        if (sebranaVec.isPrenositelna() && batoh.pridejVec(sebranaVec)) {
            // pokud se podaří přidat věc do batohu, vrátíme informaci o sebrání
            return "Sebral(a) jsi: " + sebranaVec.getNazev();
        } else {
            // pokud není místo v batohu, vrátíme informaci o plném batohu
            return "Nemůžeš vzít tuto věc.";
        }
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }
}
