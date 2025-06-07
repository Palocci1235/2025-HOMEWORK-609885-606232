package it.uniroma3.diadia.personaggi;

import java.util.TreeMap;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_FELICE = "Siccome mi hai salutata ti spedirò in " + 
			"un posto pieno di attrezzi";
	private static final String MESSAGGIO_ARRABBIATO = "Impara le buone maniere! Si saluta quando" +
			"si incontra un'altra persona!!"
			+ "\nTi meriti di finire nella peggior stanza vicina!!";
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		TreeMap<Integer, Stanza> stanza2attrezzi = new TreeMap<>();
		for (Stanza s: partita.getStanzaCorrente().getStanzeAdiacenti().values()) {
			stanza2attrezzi.put(s.getAttrezzi().size(), s);
		}
		if (haSalutato()) {
			partita.setStanzaCorrente(stanza2attrezzi.get(stanza2attrezzi.lastKey()));
			msg = MESSAGGIO_FELICE;
		}
		else {
			partita.setStanzaCorrente(stanza2attrezzi.get(stanza2attrezzi.firstKey()));
			msg = MESSAGGIO_ARRABBIATO;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().getAttrezzi().remove(attrezzo.getNome(), attrezzo);
		return new String("HAHAHAHAHAHAHA\nSperavi di ricevere qualcosa in cambio eh?");
	}
}
