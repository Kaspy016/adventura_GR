package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Veci.Klic;
import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.logika.HerniPlan;


public class PrikazOdemkni implements IPrikaz {
    private static final String NAZEV = "odemkni";
    private HerniPlan plan;

    public PrikazOdemkni(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš zadat název místnosti, kterou chceš odemknout.";
        }

        String nazevMistnosti = parametry[0];

        // Získání aktuálního prostoru, ve kterém se hráč nachází
        Prostor aktualniProstor = plan.getAktualniProstor();

        // Získání místnosti, kterou chce hráč odemknout
        Prostor odemknoutMistnost = aktualniProstor.vratSousedniProstor(nazevMistnosti);

        // Kontrola, zda hráč zadal název sousední místnosti
        if (odemknoutMistnost == null) {
            return "Tato místnost není sousední. Nelze ji tedy odemknout.";
        }

        // Kontrola, zda je místnost zamčená
        if (!odemknoutMistnost.jeZamceno()) {
            return "Tato místnost není zamčená.";
        }

        // Získání klíče odpovídajícího místnosti
        Klic spravnyKlic = odemknoutMistnost.getKlic();

        // Kontrola, zda hráč má správný klíč v batohu
        if (!plan.getBatoh().obsahujeKlic(spravnyKlic)) {
            return "Nemáš správný klíč k odemknutí této místnosti.";
        }

        // Odebrání klíče z batohu hráče
        plan.getBatoh().odeberKlic(spravnyKlic);

        // Odemknutí místnosti a umožnění hráči vstoupit
        odemknoutMistnost.odemknout();
        return "Odemkl(a) jsi místnost " + nazevMistnosti + ". Nyní do ní můžeš vstoupit.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
