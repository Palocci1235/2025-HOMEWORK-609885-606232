package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends Comando{
	
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio("\nGrazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}
}
