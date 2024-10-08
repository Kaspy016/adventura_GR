package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Batoh;
import cz.vse.adventura.logika.HerniPlan;

public class PrikazVypisBatoh implements IPrikaz {
    private static final String NAZEV = "batoh";
    private HerniPlan plan;


    public PrikazVypisBatoh(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        Batoh batoh = plan.getBatoh();
        if (batoh != null) {
            return batoh.obsahBatohu();
        } else {
            return "Nemáš žádný batoh.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
