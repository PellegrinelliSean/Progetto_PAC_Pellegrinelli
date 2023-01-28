package com.ourbooks.code.domain.utenti;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class Utente {
	private final static int MAX_LIBRI_PREF = 3;
	private static int next_id = 0;
	private String id;
	private String email;
	private String password;
	private double lat;
	private double lon;
	private double maxDist;
	private String[] libriPref;
	private HashSet<Libro> libri;
	
	public Utente(String email, String password, double lat, double lon, double maxDist, String...libriPref) {
		super();
		this.id = "UT" + next_id++;
		this.email = email;
		this.password = password;
		this.lat = lat;
		this.lon = lon;
		this.maxDist = maxDist;
		this.libriPref = new String[MAX_LIBRI_PREF];
		for (int i = 0; i < MAX_LIBRI_PREF; i++)
			this.libriPref[i] = libriPref[i];
		this.libri = new HashSet<Libro>();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getMaxDist() {
		return maxDist;
	}

	public void setMaxDist(double maxDist) {
		this.maxDist = maxDist;
	}

	public String getId() {
		return id;
	}
	
	public String[] getLibriPref() {
		return libriPref;
	}

	public void setLibriPref(String[] libriPref) {
		this.libriPref = libriPref;
	}

	public HashSet<Libro> getLibri() {
		return libri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(libriPref);
		result = prime * result + Objects.hash(email, id, lat, libri, lon, maxDist, password);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(lat) == Double.doubleToLongBits(other.lat)
				&& Objects.equals(libri, other.libri) && Arrays.equals(libriPref, other.libriPref)
				&& Double.doubleToLongBits(lon) == Double.doubleToLongBits(other.lon)
				&& Double.doubleToLongBits(maxDist) == Double.doubleToLongBits(other.maxDist)
				&& Objects.equals(password, other.password);
	}
	
}
