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
import riskid.risk.game.domain.Kartta;

/**
 *
 * @author imatias
 */
public class MapBuilderTest {
    MapBuilder mb;
    Kartta map;
    public MapBuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mb = new MapBuilder();
        map = mb.buildmap();
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
    public void asettaaviereisetOikein() {
        assertTrue(map.getAlue(1).onkoViereinen(map.getAlue(2)));
        assertFalse(map.getAlue(1).onkoViereinen(map.getAlue(9)));
        
    }
}
