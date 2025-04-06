package DiaDiaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class TestLabirinto {
	
	Labirinto labirinto;
	Stanza stanza1, stanza2;
	
	@Before
	public void setUp() throws Exception {
		labirinto = new Labirinto();
		stanza1 = new Stanza("Atrio");
		stanza2 = new Stanza("Biblioteca");
	}

	@Test
	public void testStanzaInizialeAtrio() {
		assertEquals(stanza1.getNome(), labirinto.getStanzaIngresso().getNome());
	}
	
	@Test
	public void testStanzaVincente() {
		assertEquals(stanza2.getNome(), labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testStanzaInizialeDiversaDaAtrio() {
		assertFalse(stanza2.getNome() == labirinto.getStanzaIngresso().getNome());
	}
	
	@Test
	public void testStanzaVincenteDiversaDaBiblioteca() {
		assertFalse(stanza1.getNome() == labirinto.getStanzaVincente().getNome());
	}
}
