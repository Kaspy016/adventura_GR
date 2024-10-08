package cz.vse.adventura.logika;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @autor Jarmila Pavlíčková
 * @verze pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    public void tearDown() {
        // Zde můžete provést případný úklid po testu
    }

    /***************************************************************************
     * Testuje průběh hry, po zavolání každého příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     */
    @Test
    public void testPrubehHry() {
        hra1.zpracujPrikaz("jdi sklep");
        assertEquals("sklep", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi zbrojnice");
        assertEquals("zbrojnice", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("prozkoumej");

        hra1.zpracujPrikaz("přečti hádanka_k_skladu");
        hra1.zpracujPrikaz("odpověď hora sklad");

        hra1.zpracujPrikaz("seber zelený_klíč");

        hra1.zpracujPrikaz("jdi sklad");
        assertEquals("sklad", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("prozkoumej");
        hra1.zpracujPrikaz("seber lano");

        hra1.zpracujPrikaz("jdi zbrojnice");
        assertEquals("zbrojnice", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi sklep");
        assertEquals("sklep", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi tvrz");
        assertEquals("tvrz", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi věž");
        assertEquals("věž", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("prozkoumej");
        hra1.zpracujPrikaz("polož lano");
        hra1.zpracujPrikaz("seber loučeň");
        hra1.zpracujPrikaz("seber modrý_klíč");

        hra1.zpracujPrikaz("přečti hádanka_k_trezoru");
        hra1.zpracujPrikaz("odpověď čas trezor");

        hra1.zpracujPrikaz("jdi trezor");
        assertEquals("trezor", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("prozkoumej");
        hra1.zpracujPrikaz("seber zlaťáky");

        hra1.zpracujPrikaz("jdi věž");
        assertEquals("věž", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi tvrz");
        assertEquals("tvrz", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi sklep");
        assertEquals("sklep", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("odemkni tajemná_místnost");
        hra1.zpracujPrikaz("jdi tajemná_místnost");
        assertEquals("tajemná_místnost", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("prozkoumej");
        hra1.zpracujPrikaz("seber zlaťáky");

        hra1.zpracujPrikaz("jdi sklep");
        assertEquals("sklep", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi tvrz");
        assertEquals("tvrz", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi zahrada");
        assertEquals("zahrada", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi temná_chodba");
        assertEquals("temná_chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("přečti hádanka_k_sálu");
        hra1.zpracujPrikaz("odpověď tma sál");

        hra1.zpracujPrikaz("jdi sál");
        assertEquals("sál", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("odemkni truhla");
        hra1.zpracujPrikaz("jdi truhla");
        assertEquals("truhla", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("prozkoumej");
        hra1.zpracujPrikaz("seber zlaťáky");
        hra1.zpracujPrikaz("seber červený_klíč");

        hra1.zpracujPrikaz("jdi sál");
        assertEquals("sál", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi temná_chodba");
        assertEquals("temná_chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi zahrada");
        assertEquals("zahrada", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("odemkni brána");

        hra1.zpracujPrikaz("jdi brána");
        assertEquals("brána", hra1.getHerniPlan().getAktualniProstor().getNazev());
    }
}

