package DiaDiaTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

public class TestComandoVai {
	
	private Stanza s1;
	private Stanza s2;
	private Comando comando;
	private Partita partita;
	private IO io;

	@Before
	public void setUp(){
		partita = new Partita();
		s1 = new Stanza("aula 1");
		s2 = new Stanza("aula 2");
		comando = new ComandoVai();
		io = new IOSimulator(new String[0]);
		comando.setIO(io);
	}
	
	@Test
	public void testVaiNull() {
		partita.setStanzaCorrente(s1);
		comando.esegui(partita);
		assertEquals(s1, partita.getStanzaCorrente());
	}
	
	@Test
	public void testVaiDirezioneEsistente() {
		partita.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente("sud", s2);
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals(s2, partita.getStanzaCorrente());
	}
	
	@Test
	public void testVaiDirezioneInesistente() {
		partita.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente("sud", s2);
		comando.setParametro("dietro il colle di Leopardi");
		comando.esegui(partita);
		assertNotEquals(s2, partita.getStanzaCorrente());
	}
}