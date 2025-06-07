package DiaDiaTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;

public class TestPartita {
	
	private Partita partita;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		partita = new Partita();
	}

	@Test
	public void partitaVinta() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.vinta());
	}
	
	@Test
	public void partitaNonVinta() {
		assertFalse(partita.vinta());
	}
	
	@Test
	public void partitaPersa() {
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita() && !partita.vinta());
	}
}
