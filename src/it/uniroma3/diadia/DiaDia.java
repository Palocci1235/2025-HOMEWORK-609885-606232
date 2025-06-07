package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia{


	private Partita partita;
	private IO io;

	public DiaDia(IO io) throws FileNotFoundException, FormatoFileNonValidoException{
		this.partita = new Partita();
		this.io = io;
	}

	public DiaDia(Labirinto labirinto, IO io) throws FileNotFoundException, FormatoFileNonValidoException{
		this.partita = new Partita(labirinto);
		this.io = io;
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(LetturaProperties.getBenvenuto());	
		do
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   

	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva(io);
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);

		if (this.partita.isFinita()) {
			if (this.partita.vinta())
				io.mostraMessaggio("Hai vinto!");
			if (this.partita.giocatoreIsMorto())
				io.mostraMessaggio("Game Over: CFU terminati");
			else
				return true;
		}
		return false;
	}

	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException{
		try (Scanner scanner = new Scanner(System.in)) {
			IO io = new IOConsole(scanner);
			DiaDia gioco = new DiaDia(io);
			gioco.gioca();
		}
	}
}