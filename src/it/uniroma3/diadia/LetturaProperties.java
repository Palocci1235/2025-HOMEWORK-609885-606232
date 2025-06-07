package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LetturaProperties {
	private static Properties props = new Properties();

	static {
		try (FileInputStream fis = new FileInputStream("diadia.properties")) {
			props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public String getBenvenuto() {
		return props.getProperty("MESSAGGIO_BENVENUTO");
	}

	static public int getNumeroDirezioni() {
		return Integer.parseInt(props.getProperty("NUMERO_MASSIMO_DIREZIONI"));
	}

	static public int getNumeroAttrezzi() {
		return Integer.parseInt(props.getProperty("NUMERO_MASSIMO_ATTREZZI"));
	}

	static public int getSogliaMagica() {
		return Integer.parseInt(props.getProperty("SOGLIA_MAGICA_DEFAULT"));
	}

	static public int getPesoMaxBorsa() {
		return Integer.parseInt(props.getProperty("DEFAULT_PESO_MAX_BORSA"));
	}

	static public int getCfuIniziali() {
		return Integer.parseInt(props.getProperty("CFU_INIZIALI"));
	}

	static public String getMessaggioConChi() {
		return props.getProperty("MESSAGGIO_CON_CHI");
	}
}
