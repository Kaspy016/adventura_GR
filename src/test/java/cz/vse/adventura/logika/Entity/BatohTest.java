package cz.vse.adventura.logika.Entity;

import cz.vse.adventura.Entity.Batoh;
import cz.vse.adventura.Entity.Veci.Vec;
import cz.vse.adventura.logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BatohTest {

    private Batoh batoh;

    @BeforeEach
    public void setUp() {
        batoh = new Batoh(3);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testPrazdny() {
        assertEquals("Batoh je prázdný.", batoh.obsahBatohu());
    }

    @Test
    public void testKapacity() {
        boolean result;
        for(int i = 0; i < 3; i++) {
            result = batoh.pridejVec(new Vec("Loučeň", true, "Kokot"));
            assertTrue(result);
        }

        result = batoh.pridejVec(new Vec("Loučeň", true, "Kokot"));
        assertFalse(result);
    }
}
