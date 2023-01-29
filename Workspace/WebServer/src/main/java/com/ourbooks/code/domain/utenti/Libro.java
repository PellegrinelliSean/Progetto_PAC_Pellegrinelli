package com.ourbooks.code.domain.utenti;

import java.util.UUID;

public class Libro {
	private String id;
	private String titolo;
	private int numPagine;
	private int yearPub;
	private CondLibro condizioni;
	private boolean illustrato;
	
	public Libro(String titolo, int numPagine, int yearPub, CondLibro condizioni, boolean illustrato) {
		super();
		this.id = UUID.randomUUID().toString();
		this.titolo = titolo;
		this.numPagine = numPagine;
		this.yearPub = yearPub;
		this.condizioni = condizioni;
		this.illustrato = illustrato;
	}
	
	public CondLibro getCondizioni() {
		return condizioni;
	}

	public void setCondizioni(CondLibro condizioni) {
		this.condizioni = condizioni;
	}

	public String getId() {
		return id;
	}

	public String getTitolo() {
		return titolo;
	}

	public int getNumPagine() {
		return numPagine;
	}

	public int getDataPub() {
		return yearPub;
	}

	public boolean isIllustrato() {
		return illustrato;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return id.equals(other.id);
	}
	
	
	
	
	
}
