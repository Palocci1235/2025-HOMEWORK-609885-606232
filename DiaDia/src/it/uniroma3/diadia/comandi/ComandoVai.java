package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;

public class ComandoVai implements Comando {
	
	private String direzione;
	private IO io;
	private String nome = "vai";
	
	/**
	 * esecuzione del comando
	 */
	
	@Override
	public void esegui (Partita partita) {
		if(direzione==null) {
			this.io.mostraMessaggio("\nDove vuoi andare?");
			direzione = this.io.leggiRiga();
		}
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.io.mostraMessaggio("Direzione inesistente");
		}
		else {
			if (partita.getStanzaCorrente().isBloccata()) {
				StanzaBloccata check = (StanzaBloccata) partita.getStanzaCorrente();
				if (direzione.equals(check.getDirezioneBloccata()) && !check.hasAttrezzo(check.getOggettoSbloccante())) {
					this.io.mostraMessaggio("Direzione inaccessibile: serve la chiave!");	
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
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
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
		return this.direzione;
	}
}
