package com.ourbooks.code.domain.grafo;

/**
 * La Classe VerticeUtente. Tipo dei vertici del grafo raggiungibilità utenti.
 */
public class VerticeUtente {
	
	/** L'id. */
	private final String id;
	
	/** La latitudine. */
	private double lat;
	
	/** La longitudine. */
	private double lon;
	
	/** The massima distanza percorribile in km. */
	private double maxDist;
	
	/**
	 * Istanzia un nuovo vertice utente.
	 *
	 * @param userId l'id dell'utente
	 * @param lat la latitudine
	 * @param lon la longitudine
	 * @param maxDist massima distanza percorribile in km
	 */
	public VerticeUtente(String userId, double lat, double lon, double maxDist) {
		super();
		this.id = userId;
		this.lat = lat;
		this.lon = lon;
		this.maxDist = maxDist;
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
		VerticeUtente other = (VerticeUtente) obj;
		return id.equals(other.id);
	}
}
