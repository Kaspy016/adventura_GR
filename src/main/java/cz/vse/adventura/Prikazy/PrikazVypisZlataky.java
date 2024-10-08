package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.logika.HerniPlan;

public class PrikazVypisZlataky implements IPrikaz {
    private static final String NAZEV = "zlaťáky";
    private HerniPlan plan;

    public PrikazVypisZlataky(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        Prostor aktualniProstor = plan.getAktualniProstor(); // získání aktuálního prostoru
        if (aktualniProstor != null) {
            return aktualniProstor.obsahZlataky();
        } else {
            return "V aktuálním prostoru se nenachází žádné zlaťáky.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
