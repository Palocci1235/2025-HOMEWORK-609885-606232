package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private String nomeLucernario;
	
	public StanzaBuia(String nome, String luce) {
		super(nome);
		this.nomeLucernario = luce;
	}
	
	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(nomeLucernario)) {
			return super.getDescrizione();
		}
		else
			return "Qui c'è buio pesto!";
	}
	
}
