package hr.java.vjezbe.entitet;

import java.time.LocalDate;

/**
 * Predstavlja zajednicka svojstva svih objekata prodaje koji nasljeduju ovu klasu
 * 
 * @author Patricija Kuže
 *
 */
public class Prodaja extends Entitet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4833409401637008842L;
	/**
	 * 
	 */
	Artikl artikl;
	Korisnik korisnik;
	LocalDate datumObjave;
	
	/**
	 * Inicijalizira podatke o artiklu, korisniku i datumu objave
	 * 
	 * @param artikl polje objekata tipa Artikl
	 * @param korisnik polje objekata tipa Korisnik
	 * @param datumObjave podatak o trenutnom datumu ispisivanja oglasa
	 */
	public Prodaja(Artikl artikl, Korisnik korisnik, LocalDate datumObjave, Long id) {
		super(id);
		this.artikl = artikl;
		this.korisnik = korisnik;
		this.datumObjave = datumObjave;
	}
	
	/**
	 * Dohvaca vrijednost artikla
	 * @return artikl vraca vrijednost artikla
	 */
	public Artikl getArtikl() {
		return artikl;
	}
	
	

	/**
	 * Postavlja vrijednost artikla
	 * @param artikl unesena vrijednost za artikl
	 */
	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}
	
	/**
	 * Dohvaca podatke o korisniku
	 * @return korisnik vraca podatke o korisniku
	 */
	public Korisnik getKorisnik() {
		return korisnik;
	}
	
	/**
	 * Postavlja podatke o korisniku
	 * 
	 * @param korisnik unesene vrijednosti za tip Korisnik
	 */
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	/**
	 * Dohvaca trenutni datum
	 * @return datumObjave trenutni datum
	 */
	public LocalDate getDatumObjave() {
		return datumObjave;
	}
	
	/**
	 * Postavlja vrijednost trenutog lokalnog vremena
	 * @param datumObjave trenutna vrijednost datuma 
	 */
	public void setDatumObjave(LocalDate datumObjave) {
		this.datumObjave = datumObjave;
	}
	
}
