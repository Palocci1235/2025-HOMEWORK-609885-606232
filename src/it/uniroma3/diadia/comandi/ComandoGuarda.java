package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends Comando {
	
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		if (partita.getStanzaCorrente().getPersonaggio() != null)
			this.getIO().mostraMessaggio("Nella stanza c'è il personaggio " + 
			partita.getStanzaCorrente().getPersonaggio().toString());
		this.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		this.getIO().mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
	}
}
