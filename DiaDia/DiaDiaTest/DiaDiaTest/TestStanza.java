package DiaDiaTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanza {
    
    private Stanza stanza;
    private Attrezzo attrezzo1, attrezzo2;
    
    @Before
    public void SetUp() {
        stanza = new Stanza("StanzaTest");
        attrezzo1 = new Attrezzo("Chiave", 3);
        attrezzo2 = new Attrezzo("Torcia", 6);
    }
    
    @Test
    public void testStanzaAdiacente() {
        Stanza stanzaAdiacente = new Stanza("Adiacente");
        stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
        
        assertEquals (stanzaAdiacente, stanza.getStanzaAdiacente("nord"));
    }
    
    @Test
    public void testAddAttrezzoSi() {
        assertTrue(stanza.addAttrezzo(attrezzo1));
        assertTrue(stanza.hasAttrezzo("Chiave"));
    }
    
    @Test
    public void testAddAttrezzoNo() {
    	for(int i = 0; i<10; i++) {
            stanza.addAttrezzo(new Attrezzo("pippo", 2));
        }
        assertFalse(stanza.addAttrezzo(attrezzo1));
        assertFalse(stanza.hasAttrezzo("Chiave"));
    }
    
    @Test
    public void testRemoveAttrezzoInTesta() {
        stanza.addAttrezzo(attrezzo1);
        assertTrue(stanza.removeAttrezzo(attrezzo1));
        assertFalse(stanza.hasAttrezzo("Chiave"));
    }
    
    @Test
    public void testRemoveAttrezzoInCoda() {
        stanza.addAttrezzo(attrezzo1);
        stanza.addAttrezzo(attrezzo2);
        assertTrue(stanza.removeAttrezzo(attrezzo2));
        assertFalse(stanza.hasAttrezzo("Torcia"));
    }
    
    @Test
    public void testRemoveAttrezzoInMezzo() {
        stanza.addAttrezzo(attrezzo1);
        stanza.addAttrezzo(attrezzo2);
        stanza.addAttrezzo(new Attrezzo("scala", 3));
        assertTrue(stanza.removeAttrezzo(attrezzo2));
        assertFalse(stanza.hasAttrezzo("Torcia"));
    }
    
    @Test
    public void testRemoveAttrezzoNo() {
        assertFalse(stanza.removeAttrezzo(attrezzo1));
    }
    
    @Test
    public void testGetDirezioni() {
        Stanza stanzaAdiacente=new Stanza("atrio");
        Stanza stanzaAdiacenteBis=new Stanza("biblioteca");
        stanza.impostaStanzaAdiacente("nord", stanzaAdiacente );
        stanza.impostaStanzaAdiacente("sud", stanzaAdiacenteBis);
        String[] direzioni = new String[]{"nord", "sud"};
        String[] falsissime = new String[] {"est","nord"}; 
        assertArrayEquals(direzioni, stanza.getDirezioni());
        assertNotEquals(falsissime, stanza.getDirezioni());
    }
}