package DiaDiaTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoFine;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class TestFabbricaDiComandiFisarmonica {

	private FabbricaDiComandiFisarmonica fabbrica;
	private IO io;
	private Comando comando;
	
	@Before
	public void setUp() {
		io = new IOConsole();
		fabbrica = new FabbricaDiComandiFisarmonica(io);
	}

	@Test
	public void testComandoNonValido() {
		comando = new ComandoNonValido();
		assertEquals( comando.getNome(), fabbrica.costruisciComando("etysta+palocci04").getNome());
	}
	
	@Test
	public void testComandoConParametro() {
		comando = new ComandoVai();
		comando.setParametro("nord");
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertEquals( comando.getNome(), comando.getNome());
		assertEquals( comando.getParametro(), comando.getParametro());
	}
	
	@Test
	public void testComandoSenzaParametro() {
		comando = new ComandoFine();
		assertEquals( comando.getNome(), fabbrica.costruisciComando("fine").getNome());
	}

}