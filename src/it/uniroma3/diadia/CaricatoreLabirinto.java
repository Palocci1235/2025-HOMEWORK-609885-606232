package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";

	/* prefisso della riga contenente le specifiche delle stanze bloccate */
	private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate: ";

	/* prefisso della riga contenente le specifiche delle stanze buie */
	private static final String STANZE_BUIE_MARKER = "Stanze buie: ";

	/* prefisso della riga contenente le specifiche delle stanze magiche */
	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche: ";

	/* prefisso della riga contenente le specifiche delle streghe */
	private static final String STREGHE_MARKER = "Streghe: ";

	/* prefisso della riga contenente le specifiche dei maghi */
	private static final String MAGHI_MARKER = "Maghi: ";

	/* prefisso della riga contenente le specifiche dei cani */
	private static final String CANI_MARKER = "Cani: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader stringReader) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(stringReader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiEImpostaMaghi();
			this.leggiEImpostaStreghe();
			this.leggiEImpostaCani();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			String nome = null;
			String direzioneBloccata = null;
			String oggettoSbloccante = null;
			try(Scanner scannerLinea = new Scanner(nomeStanza)){
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza"));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della direzione bloccata della stanza " + nomeStanza));
				direzioneBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'oggetto sbloccante della stanza " + nomeStanza));
				oggettoSbloccante = scannerLinea.next();
				nome2stanza.put(nome, new StanzaBloccata(nome, Direzione.valueOf(direzioneBloccata.toUpperCase()), oggettoSbloccante));
			}
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			StanzaMagica stanza = new StanzaMagica(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			String nome = null;
			String nomeLucernario = null;
			try(Scanner scannerLinea = new Scanner(nomeStanza)){
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza"));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'oggetto necessario per vederci " + nomeStanza));
				nomeLucernario = scannerLinea.next();
				nome2stanza.put(nome,new StanzaBuia(nome, nomeLucernario));
			}
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while (scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);		

		for(String specifiche : separaStringheAlleVirgole(specificheUscite)){
			try (Scanner scannerDiLinea = new Scanner(specifiche)) {	
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();

				impostaUscita(stanzaPartenza, Direzione.valueOf(dir.toUpperCase()), stanzaDestinazione);
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta " + stanzaDa);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta " + nomeA);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}

	private void leggiEImpostaMaghi() throws FormatoFileNonValidoException{
		String personaggi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);

		for(String personaggio : separaStringheAlleVirgole(personaggi)) {
			String nome = null;
			String descrizione = null;
			String nomeStanza = null;
			String oggettoMago = null;
			String peso = null;
			try (Scanner scannerLinea = new Scanner(personaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la descrizione del personaggio di nome " + nome + "."));
				descrizione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il personaggio "+nome+"."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'oggetto del mago "+nome+"."));
				oggettoMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'oggetto del mago "+nome+"."));
				peso = scannerLinea.next();
			}
			check(isStanzaValida(nomeStanza), "Stanza di destinazione sconosciuta " + nomeStanza);
			nome2stanza.get(nomeStanza).setPersonaggio(new Mago(nome, descrizione, new Attrezzo(oggettoMago,Integer.parseInt(peso))));
		}
	}

	private void leggiEImpostaStreghe() throws FormatoFileNonValidoException{
		String personaggi = this.leggiRigaCheCominciaPer(STREGHE_MARKER);

		for(String personaggio : separaStringheAlleVirgole(personaggi)) {
			String nome = null;
			String descrizione = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(personaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la descrizione del personaggio di nome " + nome + "."));
				descrizione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il personaggio "+nome+"."));
				nomeStanza = scannerLinea.next();
			}
			check(isStanzaValida(nomeStanza), "Stanza di destinazione sconosciuta " + nomeStanza);
			nome2stanza.get(nomeStanza).setPersonaggio(new Strega(nome, descrizione));
		}
	}

	private void leggiEImpostaCani() throws FormatoFileNonValidoException{
		String personaggi = this.leggiRigaCheCominciaPer(CANI_MARKER);

		for(String personaggio : separaStringheAlleVirgole(personaggi)) {
			String nome = null;
			String descrizione = null;
			String nomeStanza = null;
			String oggettoCane = null;
			String peso = null;
			try (Scanner scannerLinea = new Scanner(personaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la descrizione del personaggio di nome " + nome + "."));
				descrizione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il personaggio "+nome+"."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'oggetto del cane "+nome+"."));
				oggettoCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'oggetto del cane "+nome+"."));
				peso = scannerLinea.next();
			}
			check(isStanzaValida(nomeStanza), "Stanza di destinazione sconosciuta " + nomeStanza);
			nome2stanza.get(nomeStanza).setPersonaggio(new Mago(nome, descrizione, new Attrezzo(oggettoCane,Integer.parseInt(peso))));
		}
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}