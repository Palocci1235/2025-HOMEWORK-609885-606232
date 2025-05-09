package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.giocatore.Borsa;

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

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	//static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private Partita partita;
	private IO io;
	
	public DiaDia(IO io) {
		this.partita = new Partita();
		this.io = io;
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	/* private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if (comandoDaEseguire.getNome() != null) {
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.partita.setFinita();
				this.fine();
				return true;
			} 
			else if (comandoDaEseguire.getNome().equals("vai"))
				this.vai(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto();
			else if (comandoDaEseguire.getNome().equals("prendi"))
				this.prendi(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("posa"))
				this.posa(comandoDaEseguire.getParametro());
			else
				this.IO.mostraMessaggio("Comando sconosciuto");
		}
		else
			this.IO.mostraMessaggio("Nessun comando inserito");
		if (this.partita.isFinita()) {
			this.partita.setFinita();
			if (this.partita.vinta())
				this.IO.mostraMessaggio("\nHai vinto!");
			else
				this.IO.mostraMessaggio("\nGame Over: CFU terminati!");
			return true;
		}
			return false;
	} */
	
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica(io);
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

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	/* private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.io.mostraMessaggio(elencoComandi[i]+" ");
	} */

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	/*private void vai(String direzione) {
		
		if(direzione==null) {
			this.IO.mostraMessaggio("\nDove vuoi andare?");
			direzione = this.IO.leggiRiga();
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.IO.mostraMessaggio("Direzione inesistente");
		}
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			this.partita.getGiocatore().setCfu(this.partita.getGiocatore().getCfu() - 1);
			this.IO.mostraMessaggio("CFU rimanenti: " + this.partita.getGiocatore().getCfu());
		}
		this.IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}*/

	/**
	 * Comando "Fine".
	 */
	/* private void fine() {
		this.IO.mostraMessaggio("\nGrazie di aver giocato!");  // si desidera smettere
	}*/
	
	/* public void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.IO.mostraMessaggio("Quale attrezzo vuoi prendere?");
			nomeAttrezzo = this.IO.leggiRiga();
		}
		if (nomeAttrezzo == null) {
			this.IO.mostraMessaggio("Non hai inserito alcun attrezzo");
		}
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Attrezzo attrezzo = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		
		if (attrezzo == null) {
			this.IO.mostraMessaggio("\nAttrezzo non presente nella stanza");
		}
		else {
			if (this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
				stanzaCorrente.removeAttrezzo(attrezzo);
				this.IO.mostraMessaggio("\nHai preso: " + nomeAttrezzo + "!");
				this.IO.mostraMessaggio(stanzaCorrente.toString());
				this.IO.mostraMessaggio(borsa.toString());
			}
			else {
				if (this.partita.getGiocatore().getBorsa().getPeso() + attrezzo.getPeso() > 10)
					this.IO.mostraMessaggio("\nCarico massimo superato");
				else
					this.IO.mostraMessaggio("\nBorsa Piena");
			}
		}
	} */
	
	/* public void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.IO.mostraMessaggio("Quale attrezzo vuoi posare?");
			nomeAttrezzo = this.IO.leggiRiga();
		}
		if (nomeAttrezzo == null) {
			this.IO.mostraMessaggio("Non hai inserito alcun attrezzo");
		}
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		Attrezzo attrezzo = borsa.getAttrezzo(nomeAttrezzo);
		if (attrezzo == null) {
			this.IO.mostraMessaggio("\nAttrezzo non presente nella borsa");
		}
		else {
			if (stanzaCorrente.addAttrezzo(attrezzo)) {
				borsa.removeAttrezzo(nomeAttrezzo);
				this.IO.mostraMessaggio("\nHai posato: " + nomeAttrezzo + "!");
				this.IO.mostraMessaggio(stanzaCorrente.toString());
				this.IO.mostraMessaggio(borsa.toString());
			}
			else
				this.IO.mostraMessaggio("\nStanza Piena");
		}
	} */
	
	public static void main(String[] argc) {
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}