package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
/**
 * Predstavlja zajednicka svojsta svih artikala koja nastanu nasljedivanjem ove klase
 * 
 * @author Patricija Kuže
 *
 */
public abstract class Artikl extends Entitet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6659741396203830378L;
	/**
	 * 
	 */
	private String naslov;
	private String opis;
	private BigDecimal cijena;
	Stanje stanje;
	
	/**
	 * Inicijalizira naslov, opis i cijenu trazenog artikla
	 * 
	 * @param naslov vraca naslov artikla
	 * @param opis vraca opis artikla
	 * @param cijena vraca cijenu artikla
	 * @param stanje vraca stanje artikla
	 */
	public Artikl(String naslov, String opis, BigDecimal cijena, Stanje stanje, Long id) {
		super(id);
		this.naslov = naslov;
		this.opis = opis;
		this.cijena = cijena;
		this.stanje = stanje;
	}
	
	/**Poziva apstraktnu metodu tekstOglasa koji ispisuje podatke o artiklu
	 * 
	 * @return
	 */
	public abstract String tekstOglasa();
	
	/**
	 * Dohvaca naslov artikla
	 * @return naslov vraca naslov
	 */
	public String getNaslov() {
		return naslov;
	}
	
	/**
	 * Postavlja naslov artikla
	 * @param naslov vraca naslov artikla
	 */
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	
	/**
	 * Dohvaca opis artikla
	 * @return opis vraca opis
	 */
	public String getOpis() {
		return opis;
	}
	
	/**
	 * Postavlja opis artikla
	 * @param opis vraca opis
	 */
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	/**
	 * Dohvaca cijenu artikla
	 * @return cijena vraca cijenu
	 */
	public BigDecimal getCijena() {
		return cijena;
	}
	
	/**
	 * Postavlja cijenu artikla 
	 * @param cijena vraca cijenu artikla
	 */
	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}

	/**
	 * Dohvaca stanje artikla
	 * @return vraca stanje artikla
	 */
	public Stanje getStanje() {
		return stanje;
	}

	/**
	 * Postavlja stanje artikla
	 * @param stanje vraca stanje artikla
	 */
	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	/**
	 * Generirana metoda hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cijena == null) ? 0 : cijena.hashCode());
		result = prime * result + ((naslov == null) ? 0 : naslov.hashCode());
		result = prime * result + ((opis == null) ? 0 : opis.hashCode());
		result = prime * result + ((stanje == null) ? 0 : stanje.hashCode());
		return result;
	}

	/**
	 * Generirana metoda equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artikl other = (Artikl) obj;
		if (cijena == null) {
			if (other.cijena != null)
				return false;
		} else if (!cijena.equals(other.cijena))
			return false;
		if (naslov == null) {
			if (other.naslov != null)
				return false;
		} else if (!naslov.equals(other.naslov))
			return false;
		if (opis == null) {
			if (other.opis != null)
				return false;
		} else if (!opis.equals(other.opis))
			return false;
		if (stanje != other.stanje)
			return false;
		return true;
	}
}
