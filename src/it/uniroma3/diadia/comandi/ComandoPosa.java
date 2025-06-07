package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends Comando{

	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		if (nomeAttrezzo == null) {
			this.getIO().mostraMessaggio("Quale attrezzo vuoi posare?");
			nomeAttrezzo = this.getIO().leggiRiga();
		}
		if (nomeAttrezzo == null) {
			this.getIO().mostraMessaggio("Non hai inserito alcun attrezzo");
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		Attrezzo attrezzo = borsa.getAttrezzo(nomeAttrezzo);
		if (attrezzo == null) {
			this.getIO().mostraMessaggio("\nAttrezzo non presente nella borsa");
		}
		else {
			if (stanzaCorrente.addAttrezzo(attrezzo)) {
				borsa.removeAttrezzo(nomeAttrezzo);
				this.getIO().mostraMessaggio("\nHai posato: " + nomeAttrezzo + "!");
			}
			else
				this.getIO().mostraMessaggio("\nStanza Piena");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
