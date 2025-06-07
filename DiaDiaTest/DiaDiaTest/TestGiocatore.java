package DiaDiaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

public class TestGiocatore {
    private Giocatore giocatore;

    @Before
    public void setUp() {
        giocatore = new Giocatore();
    }

    @Test
    public void testCfuIniziali() {
        assertEquals(20, giocatore.getCfu());
    }

    @Test
    public void testSetCfu() {
        giocatore.setCfu(10);
        assertEquals(10, giocatore.getCfu());
    }
    @Test
    public void testBorsaNonNull() {
        assertNotNull(giocatore.getBorsa());
    }

    @Test
    public void testSetBorsa() {
        Borsa nuovaBorsa = new Borsa();
        giocatore.setBorsa(nuovaBorsa);
        assertSame(nuovaBorsa, giocatore.getBorsa());
    }
}