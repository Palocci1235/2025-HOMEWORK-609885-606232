package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_MORSO = "GRRRR\nIl cane ti ha morso levandoti 3 CFU";
	private static final String OGGETTO_DA_REGALARE = "osso";
	private Attrezzo attrezzoSuo;
	
	public Cane(String nome, String presentazione, Attrezzo attrezzoSuo) {
		super(nome, presentazione);
		this.attrezzoSuo = attrezzoSuo;
	}
	@Override
	public String agisci(Partita partita) {
		String msg = MESSAGGIO_MORSO;
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-3);
		return msg;
	}
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if (attrezzo.getNome().equals(OGGETTO_DA_REGALARE)) {
			partita.getGiocatore().getBorsa().getAttrezzi().remove(attrezzo.getNome(), attrezzo);
			partita.getStanzaCorrente().addAttrezzo(attrezzoSuo);
			msg = new String("Lick Lick\nIl cane ti ha leccato e ha lasciato a terra un oggetto");
		}
		else {
			msg = new String (this.agisci(partita) + "\nIl cane non ha apprezzato il regalo");
		}
		return msg;
	}

}
