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
public class AlueTest {

    Alue alue;
//    Pelaaja pelaaja;
//    Yksikko yksikko;
    Alue toinen;
    Alue kolmas;

    public AlueTest() {
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
//        pelaaja = new Pelaaja("ossi");
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void konstruktoriAsettaaIdOikein() {
        assertEquals(1, alue.getId());
    }

    @Test
    public void setViereinenAsettaaViereisetAlueet() {
        alue.setViereiset(toinen, kolmas);
        Map<Integer, Alue> alueet = new HashMap<>();
        alueet.put(2, toinen);
        alueet.put(3, kolmas);
        assertEquals(alueet, alue.getViereiset());
    }

    @Test
    public void setHallitsijaAsettaaHallitsijan() {
        Pelaaja pelaaja = new Pelaaja("ossi");
        alue.setHallitsija(pelaaja);
        assertEquals(pelaaja, alue.getHallitsija());
    }

    @Test
    public void getYksikkoPalauttaaYksikon() {
        Yksikko unit = new Yksikko(new Pelaaja("ossi"));
        alue.setYksikko(unit);
        assertEquals(unit, alue.getYksikko());
    }

    @Test
    public void onkoViereinenToimii() {
        alue.setViereiset(toinen);
        toinen.setViereiset(alue);
        assertTrue(alue.onkoViereinen(toinen));
        assertFalse(alue.onkoViereinen(kolmas));
    }

    @Test
    public void toStrngToimii() {
        Pelaaja hallitsija = new Pelaaja("ossi");
        alue.setHallitsija(hallitsija);
        alue.setYksikko(new Yksikko(hallitsija));
        assertEquals("ossi:1", alue.toString());
        assertEquals("", toinen.toString());
    }

    @Test
    public void equalsToimii() {
        
        assertFalse(alue.equals(new Pelaaja("ossi")));
        assertFalse(alue.equals(new Alue(2)));
        assertTrue(alue.equals(new Alue(1)));
    }

//    @Test
//    public void hello() {
//    }
}
