package DiaDiaTest;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePeso;
import it.uniroma3.diadia.giocatore.Borsa;

public class TestBorsa {
	
	Borsa borsa;
	Attrezzo attrezzo1, attrezzo2, attrezzo3;
	
	@Before
	public void setUp() throws Exception {
		borsa = new Borsa();
		attrezzo1 = new Attrezzo("Chiave Inglese", 3);
		attrezzo2 = new Attrezzo("Pappagallo", 3);
		attrezzo3 = new Attrezzo("Torcia", 3);
	}
	
	@Test
    public void testGetPeso() {
        borsa.addAttrezzo(attrezzo3);
        assertTrue(borsa.getPeso()==3);

    }

    @Test
    public void testGetSortedSetOrdinatoPerPeso() {
        borsa.addAttrezzo(attrezzo1);
        borsa.addAttrezzo(attrezzo2);
        Set<Attrezzo> expected = new TreeSet<>(new ComparatorePeso());
        expected.add(attrezzo1);
        expected.add(attrezzo2);
        assertArrayEquals(expected.toArray(), borsa.getSortedSetOrdinatoPerPeso().toArray());
    }
	
	@Test
	public void testAddAttrezzoSi() {
		assertTrue(this.borsa.addAttrezzo(attrezzo1));
		assertTrue(this.borsa.hasAttrezzo("Chiave Inglese"));
		
	}
	
	@Test
	public void testAddAttrezzoNo_TroppoPeso() {
		assertFalse(this.borsa.addAttrezzo(new Attrezzo("Pappagallo", 11)));
		assertFalse(this.borsa.hasAttrezzo("Pappagallo"));
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
	
	@Test
	public void testOrdinamentoPerPeso_2AttrezziStessoPeso() {
		Attrezzo test = new Attrezzo("Volano", 3);
		SortedSet<Attrezzo> s = new TreeSet<>();
		borsa.addAttrezzo(test);
		assertTrue(borsa.addAttrezzo(attrezzo3));
		s = borsa.getSortedSetOrdinatoPerPeso();
		assertEquals(2, s.size());
		Set<Attrezzo> expected = new HashSet<>();
		expected.add(attrezzo3);
		expected.add(test);
		assertEquals(expected, s);
	}
}
