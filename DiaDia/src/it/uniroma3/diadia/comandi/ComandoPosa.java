package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {

	private String nomeAttrezzo;
	private IO io;
	private String nome = "posa";
	
	@Override
	public void esegui(Partita partita) {
		if (nomeAttrezzo == null) {
			this.io.mostraMessaggio("Quale attrezzo vuoi posare?");
			nomeAttrezzo = this.io.leggiRiga();
		}
		if (nomeAttrezzo == null) {
			this.io.mostraMessaggio("Non hai inserito alcun attrezzo");
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		Attrezzo attrezzo = borsa.getAttrezzo(nomeAttrezzo);
		if (attrezzo == null) {
			this.io.mostraMessaggio("\nAttrezzo non presente nella borsa");
		}
		else {
			if (stanzaCorrente.addAttrezzo(attrezzo)) {
				borsa.removeAttrezzo(nomeAttrezzo);
				this.io.mostraMessaggio("\nHai posato: " + nomeAttrezzo + "!");
				this.io.mostraMessaggio(borsa.toString());
			}
			else
				this.io.mostraMessaggio("\nStanza Piena");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return nome ;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
