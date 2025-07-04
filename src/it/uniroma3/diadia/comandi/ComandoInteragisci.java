package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.LetturaProperties;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends Comando {

	private String messaggio;
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			this.getIO().mostraMessaggio(this.messaggio);

		} 
		else 
			this.getIO().mostraMessaggio(LetturaProperties.getMessaggioConChi());
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}
