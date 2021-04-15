package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja svojstva kategorija koja nastaju nasljedivanjem ove klase,a to su naziv i set objekata artikli
 * 
 * @author Patricija Kuže
 *
 */
public class Kategorija<T extends Artikl> {
	
	
	String naziv;
	Long id;
	private List<T> listaArtikala;
	
	/**
	 * Inicijalizira podatke o nazivu i polju objekata artikli
	 * @param naziv naziv kategorije
	 * @param artikli lista artikala
	 */
	public Kategorija(String naziv, Long id) {
		super();
		this.listaArtikala = new ArrayList<>();
		this.naziv = naziv;
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Dohvaca naziv kategorije
	 * @return naziv kategorije
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Postavlja vrijednost naziva kategorije
	 * @param naziv postavljena vrijednost kategorije
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
	 * Dohvaca listu artikala
	 * @return
	 */
	public List<T> getListaArtikala() {
		return listaArtikala;
	}

	/**
	 * Postavlja listu artikala
	 * @param listaArtikala
	 */
	public void setListaArtikala(List<T> listaArtikala) {
		this.listaArtikala = listaArtikala;
	}
	
	/**
	 * Dodaje artikl u listuArtikala
	 * @param objekt
	 */
	public void dodajArtikl(T objekt) {
		this.listaArtikala.add(objekt);
	}
	
	/**
	 * Dohvaca artikl na odredenom indeksu
	 * @param indeks indeks artikla
	 * @return indeks na kojoj se nalazi artikl u listi
	 */
	public T dohvatiArtikl(int indeks) {
		return getListaArtikala().get(indeks);
	}
}
