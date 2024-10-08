package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Mesec;
import cz.vse.adventura.logika.HerniPlan;

public class PrikazVypisMesec implements IPrikaz {
    private static final String NAZEV = "měšec";
    private HerniPlan plan;

    public PrikazVypisMesec(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        Mesec mesec = plan.getMesec();
        if (mesec != null) {
            return "Celkový počet zlaťáků v měšci: " + mesec.pocetZlataku();
        } else {
            return "V měšci nemáš žádné zlaťáky.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
