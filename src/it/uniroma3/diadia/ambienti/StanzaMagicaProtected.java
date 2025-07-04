package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.LetturaProperties;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {
	
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagicaProtected(String nome) {
		this(nome, LetturaProperties.getSogliaMagica());
	}
	
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.attrezzi.values().size() < LetturaProperties.getNumeroAttrezzi()) {
			this.contatoreAttrezziPosati++;
			if (this.contatoreAttrezziPosati > this.sogliaMagica)
				attrezzo = this.modificaAttrezzo(attrezzo);
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		else return false;
	}
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo;
	}
}