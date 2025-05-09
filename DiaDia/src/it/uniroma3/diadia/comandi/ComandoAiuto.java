package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	
	private String nome = "aiuto";
	private IO io;
	static final String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine", "guarda"};

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			this.io.mostraMessaggio(elencoComandi[i]+" ");
	}

	@Override
	public void setParametro(String parametro) {}

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
