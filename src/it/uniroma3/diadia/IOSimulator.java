package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO{
	
	private final List<String> istruzioni;
	private final List<String> messaggi;
  
    
    public IOSimulator(List<String> istruzioni) {
    	this.istruzioni = istruzioni;
    	this.messaggi = new ArrayList<>();
    }

	@Override
	public void mostraMessaggio(String messaggio) {
    	this.messaggi.add(messaggio);
	}
	
	@Override
	public String leggiRiga() {
		return this.istruzioni.get(istruzioni.size() - 1);
	}
	
	public List<String> getMessaggi() {
		return this.messaggi;
	}
	
	public String getMessaggio(int quale) {
		return this.messaggi.get(quale);
	}
}
