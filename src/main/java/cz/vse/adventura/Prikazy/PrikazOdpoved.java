package cz.vse.adventura.Prikazy;

import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.logika.HerniPlan;

public class PrikazOdpoved implements IPrikaz {
    private static final String NAZEV = "odpověď";
    private HerniPlan plan;

    public PrikazOdpoved(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš zadat heslo k místnosti.";
        }

        String heslo = parametry[0];

        Prostor aktualniProstor = plan.getAktualniProstor();

        // Pokud hráč nezadal název sousedního prostoru, vrátíme chybovou zprávu
        if (parametry.length < 2) {
            return "Musíš zadat název sousedního prostoru, ke kterému chceš zadat heslo.";
        }

        String nazevSousednihoProstoru = parametry[1];

        // Získání sousedního prostoru, na který chce hráč použít heslo
        Prostor sousedniProstor = aktualniProstor.vratSousedniProstor(nazevSousednihoProstoru);

        // Pokud sousední prostor neexistuje, vrátíme chybovou zprávu
        if (sousedniProstor == null) {
            return "Zadaný sousední prostor neexistuje.";
        }

        // Kontrola, zda je místnost zamčená
        if (!sousedniProstor.jeZaheslovano()) {
            return "Tato místnost není zamčená.";
        }

        // Získání správného hesla pro sousední místnost
        String spravneHeslo = sousedniProstor.getHeslo();

        // Kontrola, zda hráč zadal správné heslo pro sousední místnost
        if (!heslo.equalsIgnoreCase(spravneHeslo)) {
            return "Špatné heslo.";
        }

        // Odheslování sousední místnosti a umožnění hráči vstoupit
        sousedniProstor.odheslovat();
        return "Odemkl(a) jsi místnost " + sousedniProstor.getNazev() + ". Nyní do ní můžeš vstoupit.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
