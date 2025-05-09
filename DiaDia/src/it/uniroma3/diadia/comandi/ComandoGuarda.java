package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
	private IO io;
	private String nome = "guarda";
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.io.mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
	}

	@Override
	public void setParametro(String parametro) {}

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
		return null;
	}
	
}
