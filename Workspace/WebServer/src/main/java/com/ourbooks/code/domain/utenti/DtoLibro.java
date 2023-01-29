package com.ourbooks.code.domain.utenti;

public class DtoLibro {
	private String titolo;
	private int numPagine;
	private int yearPub;
	private CondLibro condizioni;
	private boolean illustrato;
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public int getNumPagine() {
		return numPagine;
	}
	public void setNumPagine(int numPagine) {
		this.numPagine = numPagine;
	}
	public int getYearPub() {
		return yearPub;
	}
	public void setYearPub(int yearPub) {
		this.yearPub = yearPub;
	}
	public CondLibro getCondizioni() {
		return condizioni;
	}
	public void setCondizioni(CondLibro condizioni) {
		this.condizioni = condizioni;
	}
	public boolean isIllustrato() {
		return illustrato;
	}
	public void setIllustrato(boolean illustrato) {
		this.illustrato = illustrato;
	}
	
}
