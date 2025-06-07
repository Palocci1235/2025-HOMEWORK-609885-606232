package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private Direzione direzioneBloccata;
	private String oggettoSbloccante;
	
	public StanzaBloccata (String nome, Direzione direzioneBloccata, String oggettoSbloccante){
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.oggettoSbloccante = oggettoSbloccante;
		super.isBloccata = true;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(oggettoSbloccante)) {
			return this;
		}
		else
			return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(oggettoSbloccante)) {
			return super.getDescrizione();
		}
		else
			return super.getDescrizione() + "\nDirezione " + direzioneBloccata + 
				" bloccata.\nPer passare mettere " + oggettoSbloccante + " nella stanza"; 
	}
	
	public Direzione getDirezioneBloccata() {
		return this.direzioneBloccata;
	}
	
	public String getOggettoSbloccante() {
		return this.oggettoSbloccante;
	}
}
