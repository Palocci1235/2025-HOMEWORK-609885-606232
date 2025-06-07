package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.LetturaProperties;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreNome;
import it.uniroma3.diadia.attrezzi.ComparatorePeso;

public class Borsa {
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(LetturaProperties.getPesoMaxBorsa());
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}
	
	public int getPeso() {
		int peso = 0;
		for(Attrezzo a : this.attrezzi.values()) {
			peso += a.getPeso();
		}
		return peso;
	}
		
	public boolean isEmpty() {
		return this.attrezzi.values().size() == 0;
	}
			
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> output = new ArrayList<>();
		output.addAll(this.attrezzi.values());
		output.sort(new ComparatorePeso());
		return output;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		TreeSet<Attrezzo> output = new TreeSet<Attrezzo>(new ComparatoreNome());
		output.addAll(this.attrezzi.values());
		return output;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappaRaggruppata = new HashMap<>();
		for (Attrezzo a : this.attrezzi.values()) {
			if (mappaRaggruppata.containsKey(a.getPeso()))
				mappaRaggruppata.get(a.getPeso()).add(a);
			else {
				Set<Attrezzo> s = new HashSet<>();
				s.add(a);
				mappaRaggruppata.put(a.getPeso(), s);
			}
		}
		return mappaRaggruppata;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> s = new TreeSet<>(new ComparatorePeso());
		s.addAll(this.attrezzi.values());
		return s;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			SortedSet<Attrezzo> set = this.getContenutoOrdinatoPerNome();
			for (Attrezzo a : set)
				s.append(a.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}