package cz.vse.adventura.logika.Prikazy;

import cz.vse.adventura.Entity.Batoh;
import cz.vse.adventura.Entity.Prostor;
import cz.vse.adventura.Entity.Veci.Vec;
import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Hra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrikazyTest {

    private Hra hra;
    private Vec testVec;
    private Vec testNeprenositelnaVec;
    private Prostor testProstor;


    @BeforeEach
    public void setUp() {
        hra = new Hra();
        testVec = new Vec("testVec", true, "");
        testProstor = new Prostor("testProstor", "testProstor","");
        hra.getHerniPlan().setAktualniProstor(testProstor);
        testProstor.pridejVec(testVec);
    }

    @BeforeEach
    public void setUp1() {
        hra = new Hra();
        testNeprenositelnaVec = new Vec("testNeprenositelnaVec", false, "");
        testProstor = new Prostor("testProstor", "testProstor","");
        hra.getHerniPlan().setAktualniProstor(testProstor);
        testProstor.pridejVec(testNeprenositelnaVec);
    }

    @Test
    public void testSeber() {
        hra.zpracujPrikaz("seber testVec");
        assertNull(testProstor.najdiVec("testVec"));
        assertNotNull(hra.getHerniPlan().getBatoh().najdiVec("testVec"));
    }

    @Test
    public void testPoloz() {
        hra.getHerniPlan().getBatoh().odeberVec("testVec");
        hra.zpracujPrikaz("polož testVec");
        assertNotNull(testProstor.najdiVec("testVec"));
        assertNull(hra.getHerniPlan().getBatoh().najdiVec("testVec"));
    }

    @Test
    public void testJdi() {
        Prostor novyProstor = new Prostor("les", "les","");
        testProstor.setVychod(novyProstor);
        hra.getHerniPlan().setAktualniProstor(testProstor);

        assertEquals("testProstor", hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi les");
        assertEquals("les", hra.getHerniPlan().getAktualniProstor().getNazev());
    }

    @Test
    public void testNeprenositelneVeciVezmi() {
        Vec testVec = new Vec("testNeprenositelnaVec", false, "");
        hra.getHerniPlan().getAktualniProstor().pridejVec(testVec);

        assertNull(hra.getHerniPlan().getBatoh().najdiVec("testNeprenositelnaVec"));
        hra.zpracujPrikaz("seber testNeprenositelnaVec");
        assertNull(hra.getHerniPlan().getBatoh().najdiVec("testNeprenositelnaVec"));
    }
}

