package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.Entity.Veci.Vec;
import cz.vse.adventura.logika.HerniPlan;


public class PrikazPrecti implements IPrikaz {
    private static final String NAZEV = "přečti";
    private HerniPlan plan;

    public PrikazPrecti(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Napiš, co chceš přečíst.";
        }

        String nazevVeci = parametry[0];// Získání názvu věci z parametrů příkazu
        Prostor aktualniProstor = plan.getAktualniProstor(); // Získání aktuálního prostoru


        Vec hledanaVec = aktualniProstor.najdiVec(nazevVeci); // Hledání věci v aktuálním prostoru
        // Kontrola, zda byla věc nalezena v prostoru
        if (hledanaVec != null) {
            return hledanaVec.getText();
        } else {
            return "Taková věc v místnosti není.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
