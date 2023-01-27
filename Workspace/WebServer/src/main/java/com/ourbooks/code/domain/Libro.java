package com.ourbooks.code.domain;

import java.util.Date;
import java.util.Objects;

public class Libro {
	private static int next_id = 0;
	private String id;
	private String titolo;
	private int numPagine;
	private Date dataPub;
	private CondLibro condizioni;
	private boolean illustrato;
	
	public Libro(String titolo, int numPagine, Date dataPub, CondLibro condizioni, boolean illustrato) {
		super();
		this.id = "LB" + next_id++;
		this.titolo = titolo;
		this.numPagine = numPagine;
		this.dataPub = dataPub;
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

	public Date getDataPub() {
		return dataPub;
	}

	public boolean isIllustrato() {
		return illustrato;
	}

	@Override
	public int hashCode() {
		return Objects.hash(condizioni, dataPub, id, illustrato, numPagine, titolo);
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
		return condizioni == other.condizioni && Objects.equals(dataPub, other.dataPub) && Objects.equals(id, other.id)
				&& illustrato == other.illustrato && numPagine == other.numPagine
				&& Objects.equals(titolo, other.titolo);
	}
	
	
	
	
	
}
