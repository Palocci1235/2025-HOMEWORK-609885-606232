package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;

public class ComandoVai extends Comando{

	/**
	 * esecuzione del comando
	 */

	@Override
	public void esegui (Partita partita) {
		if(this.getParametro()==null) {
			this.getIO().mostraMessaggio("\nDove vuoi andare?");
			this.setParametro(this.getIO().leggiRiga());
		}
		Stanza prossimaStanza = null;
		Direzione direzione;
		try {
			direzione = Direzione.valueOf(getParametro().toUpperCase());
		} 
		catch (IllegalArgumentException e) {
			this.getIO().mostraMessaggio("Direzione inesistente");
			return;
		}
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (partita.getStanzaCorrente().isBloccata()) {
			StanzaBloccata check = (StanzaBloccata) partita.getStanzaCorrente();
			if (Direzione.valueOf(getParametro().toUpperCase()).equals(check.getDirezioneBloccata()) && !check.hasAttrezzo(check.getOggettoSbloccante())) {
				this.getIO().mostraMessaggio("Direzione inaccessibile: serve la chiave!");	
			}
			else {
				partita.setStanzaCorrente(prossimaStanza);
				partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
			}
		}
		else {
			partita.setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		}
	}
}
