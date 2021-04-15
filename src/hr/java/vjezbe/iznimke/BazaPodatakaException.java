package hr.java.vjezbe.iznimke;

/**
 * Baca iznimke vezane za baze podataka
 * 
 * @author Patricija Kuže
 *
 */
public class BazaPodatakaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5957358737736255428L;

	/**
	 * Konstruktor bez parametara
	 */
	public BazaPodatakaException() {
		super("Dogodila se pogreska u radu s bazom!");
	}
	
	/**
	 * Konstruktor koji prima poruku o pogresci
	 * @param message
	 */
	public BazaPodatakaException(String message) {
		super(message);
	}
	
	/**
	 * Konstruktor koji prima oba parametra
	 * @param message
	 * @param cause
	 */
	public BazaPodatakaException (String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Konstruktor koji prima uzrocnu iznimku
	 * @param cause
	 */
	public BazaPodatakaException(Throwable cause) {
		super(cause);
	}
}
