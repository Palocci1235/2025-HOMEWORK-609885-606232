package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends Comando{

	@Override
	public void esegui(Partita partita) {
		if (partita.getStanzaCorrente().getPersonaggio() == null) {
			this.getIO().mostraMessaggio("Chi stai salutando?...Non c'è nessuno...");
			return;
		}
		this.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
	}

}
