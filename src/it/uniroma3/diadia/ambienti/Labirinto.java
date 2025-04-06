package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella un labirinto, indicando
 * la posizione delle singole stanze all'interno
 * della struttura del dia.
 * Da anche informazioni sugli attrezzi presenti nelle stanze.
 * @author Alessandro Paolucci e Simone Pallante
 * @see Stanza
 * @see Attrezzo
 * @version Base
 */
public class Labirinto {

    private Stanza stanzaIngresso;
    private Stanza stanzaVincente;

    public Labirinto() {
        init();
    }

/** 
 * Inizializza il labirinto, 
 * creando le stanze e collegandole 
 */
  
    private void init() {
    	
    	// crea gli attrezzi 
    	Attrezzo lanterna = new Attrezzo("lanterna", 3);
    	Attrezzo osso = new Attrezzo("osso", 1);

        // crea stanze del labirinto 
        Stanza atrio = new Stanza("Atrio");
        Stanza aulaN11 = new Stanza("Aula N11");
        Stanza aulaN10 = new Stanza("Aula N10");
        Stanza laboratorio = new Stanza("Laboratorio Campus");
        Stanza biblioteca = new Stanza("Biblioteca");

        // collega le stanze
        atrio.impostaStanzaAdiacente("nord", biblioteca);
        atrio.impostaStanzaAdiacente("est", aulaN11);
        atrio.impostaStanzaAdiacente("sud", aulaN10);
        atrio.impostaStanzaAdiacente("ovest", laboratorio);
        aulaN11.impostaStanzaAdiacente("ovest", atrio);
        aulaN11.impostaStanzaAdiacente("est", laboratorio);
        aulaN10.impostaStanzaAdiacente("nord", atrio);
        aulaN10.impostaStanzaAdiacente("est", aulaN11);
        aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
        laboratorio.impostaStanzaAdiacente("est", atrio);
        laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
        biblioteca.impostaStanzaAdiacente("sud", atrio);

        // pone gli attrezzi nelle stanze
        aulaN10.addAttrezzo(lanterna);
        atrio.addAttrezzo(osso);

        //imposta le stanze di uscita e di ingresso
        this.stanzaIngresso = atrio;
        this.stanzaVincente = biblioteca;
    }

    public Stanza getStanzaIngresso() {
        return stanzaIngresso;
    }

    public Stanza getStanzaVincente() {
        return stanzaVincente;
    }
}