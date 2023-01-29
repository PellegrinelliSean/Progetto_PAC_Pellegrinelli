package com.ourbooks.code.domain.utenti;

import java.util.List;

public class DtoUtente {
	private String email;
	private String password;
	private double lat;
	private double lon;
	private double maxDist;
	private String[] libriDesiderati;
	private List<Libro> libri;
	
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
	public String[] getLibriDesiderati() {
		return libriDesiderati;
	}
	public void setLibriDesiderati(String[] libriDesiderati) {
		this.libriDesiderati = libriDesiderati;
	}
	public List<Libro> getLibri() {
		return libri;
	}
	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}
	
}
