/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskid.risk.game.domain;

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
public class PelaajaTest {

    Pelaaja pelaaja;

    public PelaajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pelaaja = new Pelaaja("ossi");
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void konstruktoriAsettaaNimenOikein() {
    assertEquals("ossi", pelaaja.toString());
    }

    @Test
    public void reserviinLisaaminenToimii() {
        pelaaja.lisaaReserviin(5);
        assertEquals(5, pelaaja.getReservi());
    }
    @Test
    public void reservistaPoistaminenToimii() {
        pelaaja.lisaaReserviin(5);
        pelaaja.poistaReservista(5);
        assertEquals(0, pelaaja.getReservi());
    }
//     @Test
//     public void hello() {}
}
