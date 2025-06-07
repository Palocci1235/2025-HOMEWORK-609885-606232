package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi extends Comando {
	
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		if (nomeAttrezzo == null) {
			this.getIO().mostraMessaggio("Quale attrezzo vuoi prendere?");
			nomeAttrezzo = this.getIO().leggiRiga();
		}
		if (nomeAttrezzo == null) {
			this.getIO().mostraMessaggio("Non hai inserito alcun attrezzo");
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Attrezzo attrezzo = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		Borsa borsa = partita.getGiocatore().getBorsa();
		if (attrezzo == null) {
			this.getIO().mostraMessaggio("\nAttrezzo non presente nella stanza");
		}
		else {
			if (borsa.addAttrezzo(attrezzo)) {
				stanzaCorrente.removeAttrezzo(attrezzo);
				this.getIO().mostraMessaggio("\nHai preso: " + nomeAttrezzo + "!");
			}
			else {
				if (borsa.getPeso() + attrezzo.getPeso() > 10)
					this.getIO().mostraMessaggio("\nCarico massimo superato");
				else
					this.getIO().mostraMessaggio("\nBorsa Piena");
			}
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
