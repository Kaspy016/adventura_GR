package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.logika.HerniPlan;


public class PrikazVypisProstor implements IPrikaz {
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;


    public PrikazVypisProstor(HerniPlan plan) {
        this.plan = plan;
    }


    @Override
    public String provedPrikaz(String... parametry) {
        Prostor aktualniProstor = plan.getAktualniProstor();
        if (aktualniProstor != null) {
            return aktualniProstor.popisVeciVProstoru();
        } else {
            return "Nemáš žádný aktuální prostor.";
        }
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }
}
