package com.ourbooks.code.domain.account;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * La Classe Utente. Data type dell'applicazione e documento nel DB.
 */
@Document("useritems")
public class Utente {
	
	/** La Costante MAX_LIBRI_DES: indica il massimo numero di libri desiderati. */
	@Transient
	private final static int MAX_LIBRI_DES= 3;
	
	/** L'id. */
	@Id
	private String id;
	
	/** L'email. */
	private String email;
	
	/** La password. */
	private String password;
	
	/** La latitudine. */
	private double lat;
	
	/** La longitudine. */
	private double lon;
	
	/** La massima distanza percorribile in km. */
	private double maxDist;
	
	/** Il titolo dei libri desiderati. */
	private String[] libriDesiderati;
	
	/** I libri disponibili per la vendita. */
	private List<Libro> libri;
	
	/** Il numero di token. */
	private int nToken;
	
	/**
	 * Istanzia un nuovo utente.
	 */
	public Utente() {
		super();
		this.id = UUID.randomUUID().toString();
		this.nToken = 0;
	}
		
	/**
	 * Istanzia un nuovo utente.
	 *
	 * @param email l'email
	 * @param password la password
	 * @param lat la latitudine
	 * @param lon la longitudinte
	 * @param maxDist la distanza massima in km
	 * @param libriDesiderati il titolo dei libri desiderati
	 */
	public Utente(String email, String password, double lat, double lon, double maxDist, String...libriDesiderati) {
		this();
		this.email = email;
		this.password = password;
		this.lat = lat;
		this.lon = lon;
		this.maxDist = maxDist;
		
		//Solo al massimo MAX_LIBRI_PREF libri possono essere indicati come preferiti
		int length = libriDesiderati.length > MAX_LIBRI_DES ? MAX_LIBRI_DES : libriDesiderati.length;
		this.libriDesiderati = new String[MAX_LIBRI_DES];
		for (int i = 0; i < length; i++)
			this.libriDesiderati[i] = libriDesiderati[i];
		
		this.libri = new LinkedList<Libro>();
	}
	
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
	 * Get dell'id.
	 *
	 * @return l'id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Set dell'id.
	 *
	 * @param id il nuovo id
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * Aggiunta di un libro tra quelli disponibili per la vendita.
	 *
	 * @param l il libro
	 * @return il libro
	 */
	public Libro addLibro(Libro l) {
		this.libri.add(l);
		return l;
	}

	/**
	 * Get del numero di token.
	 *
	 * @return il numero di token
	 */
	public int getnToken() {
		return nToken;
	}

	/**
	 * Set del numero di token.
	 *
	 * @param nToken il nuovo numero di token
	 */
	public void setnToken(int nToken) {
		this.nToken = nToken;
	}

	/**
	 * Equals.
	 *
	 * @param obj l'oggetto da confrontare
	 * @return true, se this è uguale a obj
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return id.equals(other.id);
	}
	
}
