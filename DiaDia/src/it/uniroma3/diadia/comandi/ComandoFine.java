package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	
	private IO io;
	private String nome = "fine";
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("\nGrazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro)	{}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return nome;
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
