package hr.java.vjezbe.entitet;

/**
 * Predstavlja zajednicka svojstva svih korisnika koji ce naslijediti klasu Korisnik
 * 
 * @author Patricija Kuže
 *
 */
public abstract class Korisnik extends Entitet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -111027534110574516L;
	/**
	 * 
	 */
	private String email;
	private String telefon;
	
	/**
	 * apstraktna funckija preko koje dohvacamo kontakt
	 * @return dohvatiKontakt
	 */
	public abstract String dohvatiKontakt();
	
	/**
	 * Inicijalizira podatke o emailu i telefonu korisnika
	 * 
	 * @param email vrijednost emaila korisnika
	 * @param telefon vrijednost telefona korisnika
	 */
	public Korisnik(String email, String telefon, Long id) {
		super(id);
		this.email = email;
		this.telefon = telefon;
	}
	
	/**
	 * Dohvaca uneseni mail korisnika
	 * @return email vraca email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Postavlja email korisnika
	 * @param email vraca email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Dohvaca telefon korisnika
	 * @return telefon
	 */
	public String getTelefon() {
		return telefon;
	}
	
	/**
	 * Postavlja telefon korisnika
	 * @param telefon
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
}
