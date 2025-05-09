package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore giocatore;
	
	public Partita(){
		this.labirinto = new Labirinto();
		this.stanzaCorrente = labirinto.getStanzaIngresso();
		this.finita = false;
		this.giocatore = new Giocatore();
	}

	public Stanza getStanzaVincente() {
		return labirinto.getStanzaVincente();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}
	
	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || giocatoreIsMorto();
	}
	
	public boolean giocatoreIsMorto() {
		return this.getGiocatore().getCfu() == 0;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public String toString(){
		return this.getStanzaCorrente().toString() + "\n" + this.getGiocatore().getCfu() + " CFU";
	}
}
