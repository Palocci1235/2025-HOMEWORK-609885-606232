package DiaDiaTest;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.StringReader;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;

public class TestCaricatoreLabirinto {
	private CaricatoreLabirinto caricatore;
	private StringReader stringReader;
	
	@Before
	public void setUp() throws Exception {
		this.stringReader = new StringReader("Stanze: biblioteca, N10, N11\n"
				+ "Inizio: N10\n"
				+ "Vincente: N11\n"
				+ "Attrezzi: martello 10 biblioteca, pinza 2 N10\n"
				+ "Uscite: biblioteca nord N10, biblioteca sud N11");
	}

	@Test
	public void test() throws FileNotFoundException, FormatoFileNonValidoException{
		this.caricatore = new CaricatoreLabirinto(this.stringReader);
		caricatore.carica();
	}
}
