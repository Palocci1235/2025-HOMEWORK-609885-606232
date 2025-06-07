package it.uniroma3.diadia.ambienti;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.LetturaProperties;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class StanzaProtected {
	
	private String nome;
	
    protected Map<String, Attrezzo> attrezzi;
    
    protected Map<String, StanzaProtected> stanzeAdiacenti;	//key = direzione, value = Stanza
    
	protected boolean isBloccata;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public StanzaProtected(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<> ();
        this.isBloccata = false;
    }
    
    public void impostaStanzaAdiacente(String direzione, StanzaProtected stanza) {
    	if (this.stanzeAdiacenti.containsKey(direzione)) {
    		this.stanzeAdiacenti.put(direzione, stanza);
    	}
    	else {
    		if (this.stanzeAdiacenti.keySet().size() < LetturaProperties.getNumeroDirezioni())
    			this.stanzeAdiacenti.put(new String (direzione), stanza);
    	}
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public StanzaProtected getStanzaAdiacente(String direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Collection<Attrezzo> getAttrezzi() {
        return this.attrezzi.values();
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if (this.attrezzi.keySet().size() < LetturaProperties.getNumeroAttrezzi()) {
    		this.attrezzi.put(attrezzo.getNome(), attrezzo);
    		return true;
    	}
    	return false;
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome(), attrezzo);
	}


	public Collection<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
    }
	
	public boolean isBloccata(){
		return isBloccata;
	}
	
	/**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
	
	@Override
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite:");
    	for (String direzione : this.stanzeAdiacenti.keySet())
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	if (this.attrezzi.size() != 0) {
    		risultato.append("\nAttrezzi nella stanza: ");
    		for (Attrezzo a : this.attrezzi.values()) {
    			risultato.append(a.toString()+" ");
    		}
    	}
    	else
    		risultato.append("\nNon ci sono attrezzi");
    	
    	return risultato.toString();
    }

}