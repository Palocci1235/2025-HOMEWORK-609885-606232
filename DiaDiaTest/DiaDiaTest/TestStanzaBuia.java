package DiaDiaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanzaBuia {
	private Attrezzo attrezzo1;
	private StanzaBuia spazioProfondo;
	
	@Before
	public void setUp() throws Exception {
		attrezzo1 = new Attrezzo("lampadario", 6);
		spazioProfondo = new StanzaBuia("spazio profondo", "lampadario");
	}

	@Test
	public void testGetDescrizioneBuia() {
		assertEquals(spazioProfondo.getDescrizione(), "Qui c'è buio pesto!");
	}
	
	@Test
	public void testGetDescrizioneNonBuia() {
		spazioProfondo.addAttrezzo(attrezzo1);
		assertEquals(spazioProfondo.getDescrizione(), spazioProfondo.toString());
	}
}
