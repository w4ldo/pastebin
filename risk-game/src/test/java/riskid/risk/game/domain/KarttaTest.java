/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskid.risk.game.domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import riskid.risk.game.sovelluslogiikka.MapBuilder;

/**
 *
 * @author imatias
 */
public class KarttaTest {

    Kartta kartta;
    Manner manner;
    Manner toinenManner;
    Alue alue;
    Alue toinen;
    Alue kolmas;
    MapBuilder mb;
    Kartta map;
    Pelaaja pelaaja;

    public KarttaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        alue = new Alue(1);
        toinen = new Alue(2);
        kolmas = new Alue(3);
        manner = new Manner(3, "manner", alue, toinen);
        toinenManner = new Manner(2, "toinenManner", kolmas);
        kartta = new Kartta(manner, toinenManner);
        mb = new MapBuilder();
        map = mb.buildmap();
        pelaaja = new Pelaaja("ossi");
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void konstruktoriAsettaaMantereetOikein() {
        Map<String, Manner> map = new HashMap<>();
        map.put("manner", manner);
        map.put("toinenManner", toinenManner);
        assertEquals(map, kartta.getMantereet());
    }

    @Test
    public void getAlueToimii() {

        assertEquals(new Alue(1), map.getAlue(1));
        assertEquals(new Alue(10), map.getAlue(10));
        assertEquals(new Alue(14), map.getAlue(14));
        assertEquals(new Alue(21), map.getAlue(21));
        assertEquals(new Alue(27), map.getAlue(27));
        assertEquals(new Alue(39), map.getAlue(39));
        assertEquals(null, map.getAlue(0));

    }

    @Test
    public void onkoTyhjiaAlueitaToimii() {
        assertTrue(map.onkoTyhjiaAlueita());
        for (int i = 1; i < 43; i++) {
            map.getAlue(i).setYksikko(new Yksikko(new Pelaaja("ossi")));
        }
        assertFalse(map.onkoTyhjiaAlueita());
    }
    @Test
    public void lisajoukkojenLaskeminenToimii() {
        assertEquals(0, map.laskePelaajanLisajoukot(pelaaja));
        for (int i = 1; i < 43; i++) {
            map.getAlue(i).setHallitsija(pelaaja);
        }
        assertEquals(36, map.laskePelaajanLisajoukot(pelaaja));
    }
    @Test
    public void voittaakoToimii() {
        assertFalse(map.voittaako(pelaaja));
        for (int i = 1; i < 43; i++) {
            map.getAlue(i).setHallitsija(pelaaja);
        }
        assertTrue(map.voittaako(pelaaja));
    }
//     @Test
//     public void hello() {}
}
