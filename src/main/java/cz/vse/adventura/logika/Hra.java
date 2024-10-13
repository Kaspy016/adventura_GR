package cz.vse.adventura.logika;

import cz.vse.adventura.Entity.Batoh;
import cz.vse.adventura.Pozorovatel;
import cz.vse.adventura.PredmetPozorovani;
import cz.vse.adventura.Prikazy.*;
import cz.vse.adventura.ZmenaHry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Batoh batoh;

    private Map<ZmenaHry, Set<Pozorovatel>> seznamPozorovatelu = new HashMap<>();

    public Hra() {
        herniPlan = new HerniPlan();
        this.batoh = herniPlan.getBatoh();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVypisBatoh(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVypisProstor(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVypisMesec(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVypisZlataky(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdemkni(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPrecti(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdpoved(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazHadanka(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazVypisVychody(herniPlan));
        for(ZmenaHry zmenaHry : ZmenaHry.values()) {
            seznamPozorovatelu.put(zmenaHry, new HashSet<>());
        }
    }



    public String vratUvitani() {
        return "Vítejte!\n" +
               "Vítej ve starobylém hradu plném tajemství a nebezpečí! \n" +
                "Tvým úkolem je najít 100 zlaťáků, najít klíč k bráně a utéct. Objevuj, sbírej a utíkej před nebezpečím!\n" +
               "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    

    public String vratEpilog() {
        return "Dík, že jste si zahráli.";
    }
    

     public boolean konecHry() {
        return konecHry;
    }


    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani = " .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
            // Zkontrolujeme, zda hráč vstoupil do prostoru "brána"

            /*if (herniPlan.getAktualniProstor() == herniPlan.getViteznyProstor()) {*/
            if (konecHry()) {
                /* setKonecHry(true); */
                return "Gratuluji! Utekli jste z hradu s " + herniPlan.getMesec().pocetZlataku() + " zlaťáky.";
            }
        } else {
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    

     public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
        upozorniPozorovatele(ZmenaHry.KONEC_HRY);
    }
    

     public HerniPlan getHerniPlan(){
        return herniPlan;
     }

    private void upozorniPozorovatele(ZmenaHry zmenaHry) {
        for(Pozorovatel pozorovatel : seznamPozorovatelu.get(zmenaHry)) {
            pozorovatel.aktualizuj();
        }
    }

    @Override
    public void registruj(ZmenaHry zmenaHry, Pozorovatel pozorovatel) {
        seznamPozorovatelu.get(zmenaHry).add(pozorovatel);
    }
}

