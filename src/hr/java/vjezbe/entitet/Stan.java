package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * Klasa Stan nasljeduje klasu Artikl i implementira sucelje Nekretnine
 * 
 * @author Patricija Kuže
 *
 */
public class Stan extends Artikl implements Nekretnina{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2955309812650719724L;
	private static final Logger logger = LoggerFactory.getLogger(Stan.class);
	private BigDecimal kvadratura;

	public static int ID_TIP_ARTIKLA = 3;
	
	public Stan(String naslov, String opis, BigDecimal cijena, Stanje stanje, BigDecimal kvadratura, Long id) {
		super(naslov, opis, cijena, stanje, id);
		this.kvadratura = kvadratura;
	}

	public BigDecimal getKvadratura() {
		return kvadratura;
	}

	public void setKvadratura(BigDecimal kvadratura) {
		this.kvadratura = kvadratura;
	}
	
	
	
	/**
	 * Vraca tekst oglasa
	 */
	@Override
	public String tekstOglasa() {
		BigDecimal cijena = null;
		
		
		String oglas = "Naslov nekretnine"
				+ ": " + getNaslov() + "\n"
				+ "Opis nekretnine: " + getOpis() + "\n"
				+ "Kvadratura nekretnine: " + kvadratura + "\n"
				+ "Porez na nekretnine: ";
		
		try {
			cijena = izracunajPorez(getCijena());
			oglas += cijena;
		} catch (CijenaJePreniskaException e) {
			logger.error("Cijena mora biti veca od 10000.", e);
			oglas += "Cijena mora biti veca od 10000.";
		}
		
		oglas += "\nCijena nekretnine: " + getCijena();
		System.out.println("Stanje nekretnine: " + getStanje());
				 
		return oglas;
	}
}
