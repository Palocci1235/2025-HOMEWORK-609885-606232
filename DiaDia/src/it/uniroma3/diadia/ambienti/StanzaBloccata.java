package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;
	private String oggettoSbloccante;
	
	public StanzaBloccata (String nome, String direzioneBloccata, String oggettoSbloccante){
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.oggettoSbloccante = oggettoSbloccante;
		this.isBloccata = true;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
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
	
	public String getDirezioneBloccata() {
		return this.direzioneBloccata;
	}
	
	public String getOggettoSbloccante() {
		return this.oggettoSbloccante;
	}
}
