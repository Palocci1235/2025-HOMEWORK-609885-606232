package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class Comando{
	private IO getIO;
	private String parametro;
	
	public abstract void esegui(Partita partita);
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	public void setIO(IO io) {
		this.getIO = io;
	}
	
	public IO getIO() {
		return this.getIO;
	}
}
