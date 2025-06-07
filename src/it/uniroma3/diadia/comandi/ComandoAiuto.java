package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends Comando{
	
	static final String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine", 
											"guarda", "saluta", "interagisci", "regala"};

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			this.getIO().mostraMessaggio(elencoComandi[i]+" ");
	}
}
