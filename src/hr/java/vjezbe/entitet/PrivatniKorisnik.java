package hr.java.vjezbe.entitet;

/**
 * Predstavlja zajednicka svojstva svih privatnih korisnika koji nastanu nasljedivanjem ove klase
 * 
 * @author Patricija Kuže
 *
 */
public class PrivatniKorisnik extends Korisnik{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6168083692702014940L;
	
	public static final int ID_TIPA = 1;
	/**
	 * 
	 */
	private String ime;
	private String prezime;
	
	/**
	 * Inicijalizira podatke o imenu, prezimenu, emailu i telefonu privatnog korisnika
	 * @param ime podatak o imenu privatnog korisnika
	 * @param prezime podatak o prezimenu privatnog korisnika
	 * @param email podatak o emailu privatnog korisnika
	 * @param telefon podatak o telefonu privatnog korisnika
	 */
	public PrivatniKorisnik(String ime, String prezime, String email, String telefon, Long id) {
		super(email, telefon, id);
		this.ime = ime;
		this.prezime = prezime;
	}
	
	/**
	 * Dohvaca ime korisnika
	 * @return ime uneseni podatak
	 */
	public String getIme() {
		return ime;
	}
	
	/**
	 * Postavlja ime privatnog korisnika
	 * @param ime unesena vrijednost imena
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	/**
	 * Dohavaca prezime korisnika
	 * @return prezime uneseni podatak
	 */
	public String getPrezime() {
		return prezime;
	}
	
	/**
	 * Postavlja vrijednost prezimena
	 * @param prezime uneseni podatak
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	/**
	 * Ispisuje osobne podatke kao sto su ime, prezime, email i telefon
	 */
	@Override
	public String dohvatiKontakt() {
		return "Osobni podaci prodavatelja: " + ime + " " + prezime + ", email: " + getEmail() + ", tel: " + getTelefon();
	}

}
