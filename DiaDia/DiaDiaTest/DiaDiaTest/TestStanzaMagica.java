package DiaDiaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanzaMagica {
	
	private Attrezzo attrezzo1;
	private StanzaMagica magicland;

	@Before
	public void setUp() throws Exception {
		attrezzo1 = new Attrezzo("chiave", 3);
		magicland = new StanzaMagica("magicland");
	}

	@Test
	public void testAddAttrezzo() {
		magicland.addAttrezzo(attrezzo1);
		assertTrue(magicland.hasAttrezzo(attrezzo1.getNome()));
	}
	
	@Test
	public void testModificaAttrezzo() {
		magicland.setContatoreAttrezziPosati(4);
		magicland.addAttrezzo(attrezzo1);
		assertTrue(magicland.hasAttrezzo("evaihc"));
		assertEquals(magicland.getAttrezzo("evaihc").getPeso(), 6);
	}
	
	@Test
	public void testAddSuStanzaPiena() {
		for (int i=0;i<10;i++) {
			magicland.addAttrezzo(new Attrezzo("ciao", 0));
		}
		assertFalse(magicland.addAttrezzo(attrezzo1));
		assertFalse(magicland.hasAttrezzo(attrezzo1.getNome()));
	}
}
