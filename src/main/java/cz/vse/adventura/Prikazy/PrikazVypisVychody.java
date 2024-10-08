package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.logika.HerniPlan;

public class PrikazVypisVychody implements IPrikaz {
    private static final String NAZEV = "východy";
    private final HerniPlan herniPlan;

    public PrikazVypisVychody(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        return herniPlan.getAktualniProstor().popisVychodu();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
