package DiaDiaTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;
import org.junit.Before;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPrendi;


public class TestComandoPrendi {

	private Partita partita;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Comando comando;
	private IO io;
	
	@Before
	public void setUp()throws FileNotFoundException, FormatoFileNonValidoException{
		partita = new Partita();
		attrezzo1 = new Attrezzo("Chiave", 2);
		attrezzo2 = new Attrezzo("Torcia", 11);
		comando = new ComandoPrendi();
		io = new IOConsole(new Scanner(System.in));
		comando.setIO(io);
	}
	
	@Test
	public void testAttrezzoPreso() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo1);
		comando.setParametro("Chiave");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("Chiave"));
	}
	
	@Test
	public void testAttrezzoNonPresente() {
		comando.setParametro("Chiave");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("Chiave"));
	}
	
	@Test
	public void testAttrezzoPesante() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo2);
		comando.setParametro("Torcia");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("Torcia"));
	}

}