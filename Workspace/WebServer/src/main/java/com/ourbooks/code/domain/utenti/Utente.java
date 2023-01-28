package com.ourbooks.code.domain.utenti;

import java.util.LinkedList;
import java.util.List;

public class Utente {
	private final static int MAX_LIBRI_PREF = 3;
	private static int next_id = 0;
	private String id;
	private String email;
	private String password;
	private double lat;
	private double lon;
	private double maxDist;
	private String[] libriDesiderati;
	private List<Libro> libri;
	
	public Utente(String email, String password, double lat, double lon, double maxDist, String...libriPref) {
		super();
		this.id = "UT" + next_id++;
		this.email = email;
		this.password = password;
		this.lat = lat;
		this.lon = lon;
		this.maxDist = maxDist;
		
		//Solo al massimo MAX_LIBRI_PREF libri possono essere indicati come preferiti
		int length = libriPref.length > MAX_LIBRI_PREF ? MAX_LIBRI_PREF : libriPref.length;
		this.libriDesiderati = new String[MAX_LIBRI_PREF];
		for (int i = 0; i < length; i++)
			this.libriDesiderati[i] = libriPref[i];
		
		this.libri = new LinkedList<Libro>();
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
		return libriDesiderati;
	}

	public void setLibriPref(String[] libriPref) {
		this.libriDesiderati = libriPref;
	}

	public List<Libro> getLibri() {
		return libri;
	}
	
	public Libro addLibro(Libro l) {
		this.libri.add(l);
		return l;
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
		return id.equals(other.id);
	}
	
}
