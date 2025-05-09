package it.uniroma3.diadia;

import java.util.Scanner;

public class IOSimulator implements IO{
	
	private final String[] istruzioni;
    private final String[] messaggi;
    private int numeroMessaggi = 0;
    private int numeroIstruzioni = -1;
    
    public IOSimulator(String[] istruzioni) {
    	this.istruzioni = istruzioni;
    	this.messaggi = new String[100];
    }
	
    @Override
	public void mostraMessaggio(String messaggio) {
    	if (numeroMessaggi<messaggi.length) {
    		this.messaggi[numeroMessaggi] = messaggio;
    		this.numeroMessaggi++;	
    	}
	}
	
	@Override
	public String leggiRiga() {
		if (numeroIstruzioni<istruzioni.length) {
			this.numeroIstruzioni++;
			return istruzioni[numeroIstruzioni];
		}
		else
			return null;
	}
	
	public String[] getMessaggi() {
		return messaggi;
	}
	
	public String getMessaggio(int quale) {
		return messaggi[quale];
	}
}
