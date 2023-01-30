package com.ourbooks.code.domain.utenti;

import java.util.List;

/**
 * La Classe DtoUtente.
 * Il DTO utilizzato per ricevere le informazioni di un utente
 */
public class DtoUtente {
	
	/** L'email. */
	private String email;
	
	/** La password. */
	private String password;
	
	/** La latitudine della posizione. */
	private double lat;
	
	/** La longitudine della posizione. */
	private double lon;
	
	/** La massima distanza percorribile in km. */
	private double maxDist;
	
	/** Il titolo dei libri desiderati. */
	private String[] libriDesiderati;
	
	/** I libri disponibili per la vendita. */
	private List<Libro> libri;
	
	/**
	 * Get della email.
	 *
	 * @return l'email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Set della email.
	 *
	 * @param email la nuova email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get della password.
	 *
	 * @return la password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set della password.
	 *
	 * @param password la nuova password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Get della latitudine.
	 *
	 * @return la latitudine
	 */
	public double getLat() {
		return lat;
	}
	
	/**
	 * Set della latitudine.
	 *
	 * @param lat la nuova latitudine
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	/**
	 * Get della longitudine.
	 *
	 * @return la longitudine
	 */
	public double getLon() {
		return lon;
	}
	
	/**
	 * Set della longitudine.
	 *
	 * @param lon la nuova longitudine
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	/**
	 * Get della massima distanza percorribile in km.
	 *
	 * @return la massima distanza percorribile in km
	 */
	public double getMaxDist() {
		return maxDist;
	}
	
	/**
	 * Set della massima distanza percorribile in km.
	 *
	 * @param maxDist la nuova massima distanza percorribile in km
	 */
	public void setMaxDist(double maxDist) {
		this.maxDist = maxDist;
	}
	
	/**
	 * Get del titoli dei libri desiderati.
	 *
	 * @return i titoli dei libri desiderati
	 */
	public String[] getLibriDesiderati() {
		return libriDesiderati;
	}
	
	/**
	 * Set dei titoli dei libri desiderati.
	 *
	 * @param libriDesiderati i nuovi titoli dei libri desiderati
	 */
	public void setLibriDesiderati(String[] libriDesiderati) {
		this.libriDesiderati = libriDesiderati;
	}
	
	/**
	 * Get dei libri disponibili per la vendita.
	 *
	 * @return i libri disponibili per la vendita
	 */
	public List<Libro> getLibri() {
		return libri;
	}
	
	/**
	 * Se dei libri disponibili per la vendita.
	 *
	 * @param libri i nuovi libri disponibili per la vendita
	 */
	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}
	
}
