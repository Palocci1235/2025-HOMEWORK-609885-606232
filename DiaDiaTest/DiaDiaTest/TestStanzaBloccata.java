package DiaDiaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanzaBloccata {
	private Attrezzo attrezzo1;
	private StanzaBloccata lockerRoom;
	private Stanza laboratorio;

	@Before
	public void setUp() throws Exception {
		attrezzo1 = new Attrezzo("chiave", 3);
		lockerRoom = new StanzaBloccata("locker room", Direzione.NORD, "chiave");
		laboratorio = new Stanza("laboratorio");
		lockerRoom.impostaStanzaAdiacente(Direzione.NORD, laboratorio);
	}

	@Test
	public void testGetStanzaAdiacenteBloccata() {
		assertEquals(lockerRoom, lockerRoom.getStanzaAdiacente(Direzione.NORD));
	}

	@Test
	public void testGetStanzaAdiacenteSbloccata() {
		lockerRoom.addAttrezzo(attrezzo1);
		assertEquals(laboratorio, lockerRoom.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testGetDescrizioneBloccata() {
		assertEquals(lockerRoom.getDescrizione(), lockerRoom.toString()+ "\nDirezione " + 
					 lockerRoom.getDirezioneBloccata() + " bloccata.\nPer passare mettere " +
					 lockerRoom.getOggettoSbloccante() + " nella stanza");
	}
	
	@Test
	public void testGetDescrizioneSbloccata() {
		lockerRoom.addAttrezzo(attrezzo1);
		assertEquals(lockerRoom.getDescrizione(), lockerRoom.toString());
	}
}
