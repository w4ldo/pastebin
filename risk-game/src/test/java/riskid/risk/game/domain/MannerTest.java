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

/**
 *
 * @author imatias
 */
public class MannerTest {

    public MannerTest() {
    }

    Manner manner;
    Alue eka;
    Alue toka;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        eka = new Alue(1);
        toka = new Alue(2);
        manner = new Manner(3, "manner", eka, toka);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void konstruktoriAsettaaNimenOikein() {
        assertEquals("manner", manner.getNimi());
    }
    
    @Test
    public void konstruktoriAsettaaBonuksenOikein() {
        assertEquals(3, manner.getBonus());
    }
    
    @Test
    public void getAlueetPalauttaaAlueet() {
        Map<Integer, Alue> map = new HashMap<>();
        map.put(1, eka);
        map.put(2, toka);
        assertEquals(map, manner.getAlueet());
    }
    // @Test
    // public void hello() {}
    @Test
    public void onkoBonusToimii() {
        Pelaaja hallitsija = new Pelaaja("ossi");
        Pelaaja pelaaja = new Pelaaja("hessu");
        toka.setHallitsija(hallitsija);
        eka.setHallitsija(hallitsija);
        assertTrue(manner.onkoBonus(hallitsija));
        assertFalse(manner.onkoBonus(pelaaja));
    }
    
    @Test
    public void getAlueToimii() {
        assertEquals(eka, manner.getAlue(1));
        assertEquals(null, manner.getAlue(0));
    }
}
