package DiaDiaTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.Before;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
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
	public void setUp()throws FileNotFoundException, FormatoFileNonValidoException{
		partita = new Partita();
		s1 = new Stanza("aula 1");
		s2 = new Stanza("aula 2");
		comando = new ComandoVai();
		io = new IOSimulator(new ArrayList<String>());
		comando.setIO(io);
	}

	@Test
	public void testVaiNullEPoiInesistente() throws FileNotFoundException, FormatoFileNonValidoException{
		partita.setLabirinto(Labirinto.newBuilder("labirinto.txt").getLabirinto());
		Set<String> lista = Set.of("asdnkj");
		List<String> istruzioni = new ArrayList<String>();
		istruzioni.addAll(lista);
		io = new IOSimulator(istruzioni);
		comando = new ComandoVai();
		comando.setIO(io);
		comando.setParametro(null);
		Stanza stanzaAttesa = partita.getStanzaCorrente();
		comando.esegui(partita);
		assertEquals(stanzaAttesa, partita.getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneEsistente() {
		partita.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente(Direzione.SUD, s2);
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals(s2, partita.getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneInesistente() {
		partita.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente(Direzione.SUD, s2);
		comando.setParametro("dietro il colle di Leopardi");
		comando.esegui(partita);
		assertNotEquals(s2, partita.getStanzaCorrente());
	}
}