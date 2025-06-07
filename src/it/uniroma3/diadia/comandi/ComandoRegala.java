package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends Comando{

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		Borsa borsa = partita.getGiocatore().getBorsa();
		if (personaggio == null) {
			this.getIO().mostraMessaggio("Bel regalo...Peccato non ci sia nessuno a cui farlo");
			return;
		}
		if (this.getParametro() == null) {
			this.getIO().mostraMessaggio("Cosa vuoi regalare a " + personaggio.getNome() + "?");
			this.setParametro(this.getIO().leggiRiga());
		}
		if (borsa.getAttrezzi().containsKey(this.getParametro())) {
			this.getIO().mostraMessaggio(personaggio.riceviRegalo
					(borsa.getAttrezzi().get(this.getParametro()), partita));
		}
		else {
			this.getIO().mostraMessaggio("Ok che conta il pensiero, ma "
					+ "non puoi regalargli un attrezzo che non hai...");
		}
	}

}
