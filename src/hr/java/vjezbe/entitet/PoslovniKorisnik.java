package hr.java.vjezbe.entitet;


/**
 * Predstavlja zajednicka svojstva svih poslovnih korisnika koja nastanu nasljedivanjem ove klase
 * 
 * @author Patricija Ku�e
 *
 */
public class PoslovniKorisnik extends Korisnik{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4286867750503599512L;
	public static final int ID_TIPA = 2;
	/**
	 * 
	 */
	private String naziv;
	private String web;
	
	/**
	 * Inicijalizira podatke o emailu, telefonu, nazivu i webu o poslovnom korisniku
	 * 
	 * @param email vraca unesenu vrijednost za email poslovnog korisnika
	 * @param telefon vraca unesenu vrijednost za telefon poslovnog korisnika
	 * @param naziv vraca unesenu vrijednost za naziv poslovnog korisnika
	 * @param web vraca unesenu vrijednost za web poslovnog korisnika
	 */
	public PoslovniKorisnik(String email, String telefon, String naziv, String web, Long id) {
		super(email, telefon, id);
		this.naziv = naziv;
		this.web = web;
	}
	
	/**
	 * Dohvaca naziv poslovnog korisnika
	 * @return naziv uneseni podatak
	 */
	public String getNaziv() {
		return naziv;
	}
	
	/**
	 * Postavlja vrijednost naziva poslovnog korisnika
	 * @param naziv uneseni naziv korisnika
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	/**
	 * Dohvaca vrijednost weba korisnika
	 * @return uneseni web
	 */
	public String getWeb() {
		return web;
	}
	
	/**
	 * Postavlja vrijedsnost weba korisnika
	 * @param web unesena vrijednost weba korisnika
	 */
	public void setWeb(String web) {
		this.web = web;
	}
	
	/**
	 * Ispisuje podatke o poslovnom korisniku kao sto su naziv, web, telefon i mail
	 */
	@Override
	public String dohvatiKontakt() {
		return "Naziv tvrtke: " + naziv + ", mail: " + getEmail() + ", tel: " + getTelefon() + ", web: " + web;
	}
	
}
