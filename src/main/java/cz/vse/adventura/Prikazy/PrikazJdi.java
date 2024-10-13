package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Batoh;
import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.logika.HerniPlan;

public class PrikazJdi implements IPrikaz {
    public static final String NAZEV = "jdi";
    private HerniPlan plan;


    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }


    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // Pokud chybí druhé slovo (sousední prostor)
            return "Kam mám jít? Musíš zadat jméno východu.";
        }

        String smer = parametry[0];

        // Získání aktuálního prostoru
        Prostor aktualniProstor = plan.getAktualniProstor();

        // Získání sousedního prostoru podle zadaného směru
        Prostor sousedniProstor = aktualniProstor.vratSousedniProstor(smer);

        // Získání batohu z aktuálního prostoru
        Batoh batoh = plan.getBatoh();

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        } else if (sousedniProstor.jeZamceno()) {
            return "Tato místnost je zamčená. Potřebuješ klíč k jejímu odemknutí.";
        } else if (sousedniProstor.jeTemnota() && !batoh.obsahujeLoucen()) {
            return "Je příliš tmavá na to, abys do ní mohl vejít. Potřebuješ loučeň.";
        } else if (sousedniProstor.jeVysoko() && !batoh.obsahujeLano()) {
            return "Místnost je přílíš vysoko. Potřebuješ lano";
        } else if (sousedniProstor.jeZaheslovano() && !sousedniProstor.getHeslo().equals(smer)) {
            return "K tomuto prostoru potřebuješ heslo.";
        } else {
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

}
