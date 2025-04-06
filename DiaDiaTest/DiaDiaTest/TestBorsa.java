package DiaDiaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class TestBorsa {
	
	Borsa borsa;
	Attrezzo attrezzo1, attrezzo2, attrezzo3;
	
	@Before
	public void setUp() throws Exception {
		borsa = new Borsa();
		attrezzo1 = new Attrezzo("Chiave Inglese", 6);
		attrezzo2 = new Attrezzo("Pappagallo", 11);
		attrezzo3 = new Attrezzo("Torcia", 3);
	}
	
	@Test
	public void testAddAttrezzoSi() {
		assertTrue(this.borsa.addAttrezzo(attrezzo1));
		assertTrue(this.borsa.hasAttrezzo("Chiave Inglese"));
		
	}
	
	@Test
	public void testAddAttrezzoNo_TroppoPeso() {
		assertFalse(this.borsa.addAttrezzo(attrezzo2));
		assertFalse(this.borsa.hasAttrezzo("Pappagallo"));
	}
	
	@Test
	public void testAddAttrezzoNo_BorsaPiena() {
		for (int i=0; i<10; i++) {
			this.borsa.addAttrezzo(new Attrezzo("Torcia", 0));
		}
		assertFalse(this.borsa.addAttrezzo(attrezzo1));
	}
	
	@Test
	public void isEmptySi() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void isEmptyNo() {
		this.borsa.addAttrezzo(attrezzo1);
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	public void testRemoveAttrezzoInTesta(){
		this.borsa.addAttrezzo(attrezzo1);
		assertEquals(this.borsa.removeAttrezzo("Chiave Inglese"), attrezzo1);
		assertFalse(this.borsa.hasAttrezzo("Chiave Inglese"));
	}
	
	@Test
	public void testRemoveAttrezzoInCoda(){
		this.borsa.addAttrezzo(attrezzo1);
		this.borsa.addAttrezzo(attrezzo3);
		assertEquals(this.borsa.removeAttrezzo("Torcia"), attrezzo3);
		assertFalse(this.borsa.hasAttrezzo("Torcia"));
	}
	
	@Test
	public void testRemoveAttrezzoInMezzo(){
		Attrezzo attrezzo4 = new Attrezzo("scala", 1);
		this.borsa.addAttrezzo(attrezzo1);
		this.borsa.addAttrezzo(attrezzo3);
		this.borsa.addAttrezzo(attrezzo4);
		assertEquals(this.borsa.removeAttrezzo("Torcia"), attrezzo3);
		assertFalse(this.borsa.hasAttrezzo("Torcia"));
	}
	
	@Test
	public void testRemoveAttrezzoNo(){
		assertNull(this.borsa.removeAttrezzo("Chiave Inglese"));
	}
}
