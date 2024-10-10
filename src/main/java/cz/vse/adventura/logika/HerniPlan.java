package cz.vse.adventura.logika;

import cz.vse.adventura.Entity.*;
import cz.vse.adventura.Entity.Veci.Klic;
import cz.vse.adventura.Entity.Veci.Lano;
import cz.vse.adventura.Entity.Veci.Loucen;
import cz.vse.adventura.Entity.Veci.Vec;

public class HerniPlan {

    private Prostor aktualniProstor;
    private Batoh batoh;
    private Mesec mesec;

    public HerniPlan() {
        zalozProstoryHry();
        this.batoh = new Batoh (3);
        this.mesec = new Mesec();
        zalozProstoryHry();
    }

    // vytvářejí se jednotlivé prostory
    private void zalozProstoryHry() {
        Prostor trezor = new Prostor("trezor", "trezor","čas"); //"čas"
        Prostor truhla = new Prostor("truhla", "truhla","");
        Prostor tajemnaMistnost = new Prostor("tajemná_místnost","","");
        Prostor tvrz = new Prostor("tvrz", "tvrz","");
        Prostor vez = new Prostor("věž", "věž","");
        Prostor zahrada = new Prostor("zahrada", "zahrada","");
        Prostor sklep = new Prostor("sklep", "sklep","");
        Prostor temnaChodba = new Prostor("temná_chodba", "temná chodba","");
        Prostor zbrojnice = new Prostor("zbrojnice", "zbrojnice","");
        Prostor sal = new Prostor("sál", "sál","tma"); //"tma"
        Prostor sklad = new Prostor("sklad", "sklad","hora"); //"hora"
        Prostor brana = new Prostor("brána", "brána","");

        // Vytvoření pohyblivých věcí
        Vec lano = new Lano("lano",true);
        Vec loucen = new Loucen("loučeň",true);

        // Přidání zlaťáků do místností
        trezor.pridejZlataky(30);
        zahrada.pridejZlataky(10);
        truhla.pridejZlataky(25);
        sklad.pridejZlataky(10);
        vez.pridejZlataky(10);
        tajemnaMistnost.pridejZlataky(15);


        // Vytvoření klíče
        Klic klicBrana = new Klic("červený_klíč");
        Klic klicTruhla = new Klic("zelený_klíč");
        Klic klicTajemnaMistnost = new Klic("modrý_klíč");

        //Přiřazení klíčů k místnostem
        brana.pridatKlicMistnosti(klicBrana);
        truhla.pridatKlicMistnosti(klicTruhla);
        tajemnaMistnost.pridatKlicMistnosti(klicTajemnaMistnost);

        //Přidání věcí do místnosti
        vez.pridejVec(loucen);
        sklad.pridejVec(lano);

        // Přidání klíče do místnosti
        zbrojnice.pridejVec(klicTruhla);
        truhla.pridejVec(klicBrana);
        vez.pridejVec(klicTajemnaMistnost);

        /*
        //Nastavení temných místností
        temnaChodba.setTemna(true);

        //Nastavení vysokých místností
        vez.setVysoko(true);


        //Uzamčení místností
        brana.zamknout();
        truhla.zamknout();
        tajemnaMistnost.zamknout();
        */

        // Vytvoření nepohyblivých věcí
        Vec hadanka1 = new Vec("hádanka_k_trezoru",false,"Všechno žere, všechno se v něm ztrácí,\n" +
                "stromy, květy, zvířata i ptáci.\n" +
                "Hryže kov i pláty z ocele,\n" +
                "tvrdý kámen na prach semele.\n" +
                "Města rozvalí a krále skolí,\n" +
                "vysokánské hory svrhne do údolí.\n");
        // Odpověď = čas

        Vec hadanka2 = new Vec("hádanka_k_sálu",false,"Není ji vidět, není ji cítit,\n" +
                "není ji slyšet, nejde ji chytit.\n" +
                "Je za hvězdami a pod horami\n" +
                "a vyplňuje prázdné jámy.\n" +
                "Byla tu předtím a přijde pak\n" +
                "a nakonec ti vytře zrak.\n");
        // Odpověď = Tma

        Vec hadanka3 = new Vec("hádanka_k_skladu",false,"Kořeny má skryté v zemi,\n" +
                "vypíná se nad jedlemi,\n" +
                "stoupá pořád výš a výš,\n" +
                "ale růst ji nevidíš.\n");
        // Odpověď = Hora

        //Přidání hádanek do místností
        zbrojnice.pridejVec(hadanka3);
        temnaChodba.pridejVec(hadanka2);
        vez.pridejVec(hadanka1);

        // Přiřazují se průchody mezi prostory
        tvrz.setVychod(sklep);
        tvrz.setVychod(zahrada);
        tvrz.setVychod(vez);
        vez.setVychod(trezor);
        vez.setVychod(tvrz);
        zahrada.setVychod(temnaChodba);
        zahrada.setVychod(brana);
        zahrada.setVychod(tvrz);
        sklep.setVychod(tajemnaMistnost);
        sklep.setVychod(zbrojnice);
        sklep.setVychod(tvrz);
        trezor.setVychod(vez);
        brana.setVychod(zahrada);
        temnaChodba.setVychod(sal);
        temnaChodba.setVychod(zahrada);
        tajemnaMistnost.setVychod(sklep);
        sal.setVychod(temnaChodba);
        sal.setVychod(truhla);
        zbrojnice.setVychod(sklep);
        truhla.setVychod(sal);
        zbrojnice.setVychod(sklad);
        sklad.setVychod(zbrojnice);

        aktualniProstor = tvrz;  // hra začíná v domečku
    }

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }


    public Batoh getBatoh() {
        return batoh;
    }

    public Mesec getMesec() {
        return mesec;
    }

}
