package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Batoh;
import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.Entity.Veci.Vec;
import cz.vse.adventura.logika.HerniPlan;


public class PrikazPoloz implements IPrikaz {
    private static final String NAZEV = "polož";
    private HerniPlan plan;
    private Batoh batoh;


    public PrikazPoloz(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        // Kontrola, zda hráč zadal název věci, kterou chce položit
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (název věci k sebrání), vrátíme chybovou zprávu
            return "Co mám položit? Musíš zadat název věci.";
        }

        String nazevVeci = parametry[0]; // Získání názvu věci z parametrů příkazu
        Vec nalezenaVec = batoh.najdiVec(nazevVeci); // Hledání věci v batohu

        if (nalezenaVec == null) {
            // pokud věc není v batohu, vrátíme odpovídající zprávu
            return "Takovou věc nemáš.";
        }

        Prostor prostor = plan.getAktualniProstor();
        if (prostor.pridejVec(nalezenaVec)) {
            batoh.odeberVec(nazevVeci);
            return "Položil jsi: " + nalezenaVec.getNazev();
        } else {
            return "Nemůžeš položit tuto věc.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
