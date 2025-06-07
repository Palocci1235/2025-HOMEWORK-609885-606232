package DiaDiaTest;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoFine;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

public class TestFabbricaDiComandiRiflessiva {

	private FabbricaDiComandiRiflessiva fabbrica;
	private IO io;
	private Comando comando;
	
	@Before
	public void setUp() {
		io = new IOConsole(new Scanner(System.in));
		fabbrica = new FabbricaDiComandiRiflessiva(io);
	}

	@Test
	public void testComandoNonValido() {
		comando = new ComandoNonValido();
		assertEquals(comando.getClass(), fabbrica.costruisciComando("etysta+palocci04").getClass());
	}
	
	@Test
	public void testComandoConParametro() {
		comando = new ComandoVai();
		comando.setParametro("nord");
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertEquals("nord", comando.getParametro());
	}
	
	@Test
	public void testComandoSenzaParametro() {
		comando = new ComandoFine();
		assertEquals(comando.getClass(), fabbrica.costruisciComando("fine").getClass());
	}

}