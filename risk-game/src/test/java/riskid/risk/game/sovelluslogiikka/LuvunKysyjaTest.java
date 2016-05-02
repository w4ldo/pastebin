/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskid.risk.game.sovelluslogiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import riskid.risk.game.kayttoliittyma.GUI;

/**
 *
 * @author imatias
 */
public class LuvunKysyjaTest {

    private LuvunKysyja lk;
    private GUI gui;

    public LuvunKysyjaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        gui = new GUI();
        lk = new LuvunKysyja(gui);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void onkoLukuSallittuToimii() {
        for (int i = 1; i < 43; i++) {
            assertTrue(lk.onkoLukuSallittu(i));
        }
        assertFalse(lk.onkoLukuSallittu(0));
        assertFalse(lk.onkoLukuSallittu(43));
        assertFalse(lk.onkoLukuSallittu(999));
    }
    @Test
    public void kysyLukuaToimii() {
        gui.setTekstiKentta("asd", null, null);
        assertEquals(0, lk.kysyLukua());
        gui.setTekstiKentta("1", null, null);
        assertEquals(1, lk.kysyLukua());
    }
    @Test
    public void mihinSiirretaanToimii() {
        gui.setTekstiKentta(null, "asd", null);
        assertEquals(0, lk.mihinSiirretaan());
        gui.setTekstiKentta(null, "1", null);
        assertEquals(1, lk.mihinSiirretaan());
    }
    @Test
    public void mistaSiirretaanToimii() {
        gui.setTekstiKentta(null, null, "asd");
        assertEquals(0, lk.montakoSiirretaan());
        gui.setTekstiKentta(null, null, "1");
        assertEquals(1, lk.montakoSiirretaan());
    }
}
