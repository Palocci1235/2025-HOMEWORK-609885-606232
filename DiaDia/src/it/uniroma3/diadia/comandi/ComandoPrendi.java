package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi implements Comando{
	
	private String nomeAttrezzo;
	private IO io;
	private String nome = "prendi";
	
	@Override
	public void esegui(Partita partita) {
		
		if (nomeAttrezzo == null) {
			this.io.mostraMessaggio("Quale attrezzo vuoi prendere?");
			nomeAttrezzo = this.io.leggiRiga();
		}
		if (nomeAttrezzo == null) {
			this.io.mostraMessaggio("Non hai inserito alcun attrezzo");
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Attrezzo attrezzo = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		Borsa borsa = partita.getGiocatore().getBorsa();
		
		if (attrezzo == null) {
			this.io.mostraMessaggio("\nAttrezzo non presente nella stanza");
		}
		else {
			if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
				stanzaCorrente.removeAttrezzo(attrezzo);
				this.io.mostraMessaggio("\nHai preso: " + nomeAttrezzo + "!");
				this.io.mostraMessaggio(borsa.toString());
			}
			else {
				if (partita.getGiocatore().getBorsa().getPeso() + attrezzo.getPeso() > 10)
					this.io.mostraMessaggio("\nCarico massimo superato");
				else
					this.io.mostraMessaggio("\nBorsa Piena");
			}
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
