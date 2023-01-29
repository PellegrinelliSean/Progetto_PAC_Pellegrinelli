package com.ourbooks.code.domain.grafo;

public class VerticeUtente {
	private final String id;
	private double lat;
	private double lon;
	private double maxDist;
	
	public VerticeUtente(String userId, double lat, double lon, double maxDist) {
		super();
		this.id = userId;
		this.lat = lat;
		this.lon = lon;
		this.maxDist = maxDist;
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
