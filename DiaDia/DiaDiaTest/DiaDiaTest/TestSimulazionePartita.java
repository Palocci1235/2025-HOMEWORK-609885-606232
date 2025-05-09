package DiaDiaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

public class TestSimulazionePartita {
	
	private DiaDia gioco;
	private IOSimulator io;
	private String[] istruzioni;

	@Test
	public void testSimulazioneVittoria() {
		istruzioni = new String[] {"vai est", "vai est", "prendi lanterna", "vai ovest", "vai ovest", "vai sud",
									"posa lanterna", "guarda", "prendi chiave", "vai nord", "posa chiave", 
									"vai nord"};
		io = new IOSimulator(istruzioni);
		gioco = new DiaDia(io);
		gioco.gioca();
		assertEquals(io.getMessaggio(0), ""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.");
		for(int i=0;i<io.getMessaggi().length;i++) {
			if (io.getMessaggio(i+1) == null) {
				assertEquals("Hai vinto!", io.getMessaggio(i));
				break;
			}
		}
	}
}
