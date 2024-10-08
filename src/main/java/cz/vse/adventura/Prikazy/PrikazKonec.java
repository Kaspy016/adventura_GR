package cz.vse.adventura.Prikazy;

import cz.vse.adventura.logika.Hra;



public class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "konec";

    private Hra hra;


    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }


    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Ukončit co? Nechápu, proč jste zadal druhé slovo.";
        }
        else {
            hra.setKonecHry(true);
            return "hra ukončena příkazem konec";
        }
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }
}
