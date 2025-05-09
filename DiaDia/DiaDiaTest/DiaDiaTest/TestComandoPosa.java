package DiaDiaTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class TestComandoPosa {

	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Comando comando;

	@Before
	public void setUp(){
		partita = new Partita();
		attrezzo = new Attrezzo("Chiave", 2);
		comando = new ComandoPosa();
		io = new IOConsole();
		comando.setIO(io);
	}

	@Test
	public void testAttrezzoPosato() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("Chiave");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("Chiave"));
	}

	@Test
	public void testAttrezzoPosatoNull() {
		comando.setParametro("Chiave");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("Chiave"));
	}
	
	@Test
	public void testTroppiAttrezzi() {
		for(int i= 0; i<10;i++) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("attrezzo"+i, 1));
		}
		
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("Chiave");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("Chiave"));
	}
}