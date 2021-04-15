package hr.java.vjezbe.entitet;

import java.io.Serializable;

/**
 * Klasa entitet
 * 
 * @author Patricija Kuže
 *
 */

public abstract class Entitet implements Serializable{
	
	private static final long serialVersionUID = 8716393212483782409L;

	private Long id;

	public Entitet(long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
