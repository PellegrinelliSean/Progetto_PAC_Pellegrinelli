package com.ourbooks.code.domain.utenti;

/**
 * La Classe DtoLibro.
 * Il DTO utilizzato per ricevere le informazioni di un libro
 */
public class DtoLibro {
	
	/** Il titolo. */
	private String titolo;
	
	/** Il numero di pagine. */
	private int numPagine;
	
	/** L'anno di pubblicazione. */
	private int yearPub;
	
	/** Le condizioni. */
	private CondLibro condizioni;
	
	/** Se e' illustrato. */
	private boolean illustrato;
	
	/**
	 * Get del titolo.
	 *
	 * @return il titolo
	 */
	public String getTitolo() {
		return titolo;
	}
	
	/**
	 * Set del titolo.
	 *
	 * @param titolo il nuovo titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	/**
	 * Get del numero di pagine.
	 *
	 * @return il numero pagine
	 */
	public int getNumPagine() {
		return numPagine;
	}
	
	/**
	 * Sets del numero di pagine.
	 *
	 * @param numPagine il nuovo numero di pagine.
	 */
	public void setNumPagine(int numPagine) {
		this.numPagine = numPagine;
	}
	
	/**
	 * Get dell'anno di pubblicazione.
	 *
	 * @return l'anno di pubblicazione
	 */
	public int getYearPub() {
		return yearPub;
	}
	
	/**
	 * Set dell'anno di pubblicazione.
	 *
	 * @param yearPub il nuovo anno di pubblicazione.
	 */
	public void setYearPub(int yearPub) {
		this.yearPub = yearPub;
	}
	
	/**
	 * Get delle condizioni.
	 *
	 * @return le condizioni
	 */
	public CondLibro getCondizioni() {
		return condizioni;
	}
	
	/**
	 * Set delle condizioni.
	 *
	 * @param condizioni le nuove condizioni
	 */
	public void setCondizioni(CondLibro condizioni) {
		this.condizioni = condizioni;
	}
	
	/**
	 * Verifica se e' illustrato.
	 *
	 * @return true, se e' illustrato
	 */
	public boolean isIllustrato() {
		return illustrato;
	}
	
	/**
	 * Set se e' illustrato.
	 *
	 * @param illustrato il nuovo valore di illustrato
	 */
	public void setIllustrato(boolean illustrato) {
		this.illustrato = illustrato;
	}
	
}
