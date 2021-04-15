package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
/**
 * Predstavlja klasu Usluga koja nasljeduje klasu Artikl
 * 
 * @author Patricija Kuže
 *
 */
public class Usluga extends Artikl{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6784866670895173248L;

	/**
	 * Inicijalizira podatke o naslovu, opisu, cijeni i stanju usluge
	 * 
	 * @param naslov podatak o naslovu artikla
	 * @param opis podatak o opisu artikla
	 * @param cijena podatak o cijeni artikla
	 * @param stanje podatak o stanju artikla
	 */
	public static int ID_TIP_ARTIKLA = 2;
	
	public Usluga(String naslov, String opis, BigDecimal cijena, Stanje stanje, Long id) {
		super(naslov, opis, cijena, stanje, id);
	}
	
	/**
	 * Ispisuje i vraca naslov, opis, cijenu i stanje usluge
	 */
	@Override
	public String tekstOglasa() {
		return "Naslov usluge: " + getNaslov() + "\n"
				+ "Opis usluge: " + getOpis() + "\n"
				+ "Cijena usluge: " + getCijena() + "\n"
				+ "Stanje usluge: " + getStanje();
	}
}
