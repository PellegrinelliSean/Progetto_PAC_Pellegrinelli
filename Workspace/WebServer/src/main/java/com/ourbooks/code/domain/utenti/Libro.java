package com.ourbooks.code.domain.utenti;

import java.util.UUID;

/**
 * La Classe Libro. Data type dell'applicazione.
 */
public class Libro {
	
	/** L'id. */
	private String id;
	
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
	 * Istanzia un nuovo libro.
	 *
	 * @param titolo il titolo
	 * @param numPagine il numero di pagine
	 * @param yearPub l'anno di pubblicazione
	 * @param condizioni le condizioni
	 * @param illustrato se e' illustrato
	 */
	public Libro(String titolo, int numPagine, int yearPub, CondLibro condizioni, boolean illustrato) {
		super();
		this.id = UUID.randomUUID().toString();
		this.titolo = titolo;
		this.numPagine = numPagine;
		this.yearPub = yearPub;
		this.condizioni = condizioni;
		this.illustrato = illustrato;
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
	 * Get dell'id.
	 *
	 * @return l'id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get del titolo.
	 *
	 * @return il titolo
	 */
	public String getTitolo() {
		return titolo;
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
	 * Get dell'anno di pubblicazione.
	 *
	 * @return l'anno di pubblicazione
	 */
	public int getYearPub() {
		return yearPub;
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
	 * Equals.
	 *
	 * @param obj l'oggetto da confrontare
	 * @return true, se this e' uguale a obj
	 */
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
