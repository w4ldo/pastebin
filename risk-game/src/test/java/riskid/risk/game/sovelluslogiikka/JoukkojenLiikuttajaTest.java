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
import riskid.risk.game.domain.Alue;
import riskid.risk.game.domain.Pelaaja;
import riskid.risk.game.domain.Yksikko;
import riskid.risk.game.kayttoliittyma.GUI;

/**
 *
 * @author imatias
 */
public class JoukkojenLiikuttajaTest {

    JoukkojenLiikuttaja jl;
    Alue eka;
    Alue toka;
    Pelaaja aa;
    Pelaaja bb;

    public JoukkojenLiikuttajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        GUI gui = new GUI();
        aa = new Pelaaja("aa");
        bb = new Pelaaja("bb");
        eka = new Alue(1);
        toka = new Alue(2);
        eka.setViereiset(toka);
        toka.setViereiset(eka);
        jl = new JoukkojenLiikuttaja(gui);
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
    public void aloitusJoukkojenSijoitusToimii() {
        aa.lisaaReserviin(5);
        assertTrue(jl.sijoitaAloitusJoukkoja(eka, aa));
        assertEquals(4, aa.getReservi());
        jl.sijoitaAloitusJoukkoja(toka, aa);
        assertFalse(jl.sijoitaAloitusJoukkoja(toka, bb));
    }

    @Test
    public void lisaJoukkojenSijoitusToimii() {
        aa.lisaaReserviin(5);
        jl.sijoitaAloitusJoukkoja(eka, aa);
        assertTrue(jl.sijoitaLisajoukkoja(eka, 1, aa));
        assertEquals(3, aa.getReservi());
        assertFalse(jl.sijoitaLisajoukkoja(eka, 1, bb));

        assertFalse(jl.sijoitaLisajoukkoja(toka, 1, aa));
        assertEquals(3, aa.getReservi());
    }

    @Test
    public void HyokkaaTaiValtaaToimii() {
        jl.sijoitaAloitusJoukkoja(eka, aa);
        jl.sijoitaAloitusJoukkoja(toka, bb);
        jl.sijoitaLisajoukkoja(eka, 5, aa);
        jl.hyokkaaTaiValtaa(eka, toka, 5);
        assertEquals(1, eka.getYksikonVahvuus());
    }

    @Test
    public void siirraOmalleAlueelleToimii() {
        Alue kolmas = new Alue(3);
        kolmas.setViereiset(eka);
        eka.setViereiset(kolmas);
        jl.sijoitaAloitusJoukkoja(kolmas, bb);
        jl.sijoitaAloitusJoukkoja(eka, aa);
        jl.sijoitaAloitusJoukkoja(toka, aa);
        jl.sijoitaLisajoukkoja(eka, 5, aa);
        assertTrue(jl.passiivinenLiike(eka, toka, 3));
        assertEquals(3, eka.getYksikonVahvuus());
        assertEquals(4, toka.getYksikonVahvuus());
        assertFalse(jl.passiivinenLiike(eka, kolmas, 2));
    }

}
