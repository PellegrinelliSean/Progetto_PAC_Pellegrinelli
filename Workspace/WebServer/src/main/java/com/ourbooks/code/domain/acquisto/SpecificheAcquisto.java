package com.ourbooks.code.domain.acquisto;

import java.util.LinkedList;

import com.ourbooks.code.domain.account.Libro;

/**
 * La Classe SpecificheAcquisto. 
 */
public class SpecificheAcquisto {
	
	/** Libro relativo all'acquisto */
	private Libro libro;

	/** Gli utenti che il compratore dovrà pagare, il primo è il venditore, gli altri sono
	 * coloro che trasporteranno il libro acquistato per un tratto del percorso. */
	private LinkedList<String> utenti;
	
	/** I token da sottrarre al compratore ed aggiungere agli utenti della lista.
	 *	In posizione i è salvato il numero di token da assegnare all'utente in posizione i */
	private LinkedList<Integer> tokens;
	
	/**
	 * Istanziazione SpecificheAcquisto.
	 * @param l libro relativo all'acquisto
	 * @param utenti utenti che il compratore deve pagare, il primo è il venditore
	 * @param tokens token da sottrarre al compratore ed aggiungere agli utenti della lista, dovutial trasporto
	 */
	public SpecificheAcquisto(Libro l, LinkedList<String> utenti, LinkedList<Integer> tokens) {
		libro = l;
		this.utenti = new LinkedList<String>();
		this.utenti.addAll(utenti);
		this.tokens = new LinkedList<Integer>();
		this.tokens.addAll(tokens);
	}
	
	/**
	 * Get del libro.
	 *
	 * @return li libro
	 */
	public Libro getLibro() {
		return libro;
	}

	/**
	 * Get della lista di utenti da pagare.
	 *
	 * @return lista di utenti
	 */
	public LinkedList<String> getUtenti() {
		return utenti;
	}

	/**
	 * Get della lista di token da scambiare tra gli utenti.
	 *
	 * @return lista di token
	 */
	public LinkedList<Integer> getTokens() {
		return tokens;
	}
	
	/**
	 * Aggiunta del valore in token del libro tra i token dovuti all'acquirente
	 * @param token relativi al valore del libro
	 */
	public void addTokenLibro(int token) {
		tokens.set(0, tokens.get(0) + token);
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
		SpecificheAcquisto other = (SpecificheAcquisto)obj;
		
		return other.libro.equals(libro) && other.utenti.equals(utenti) && other.tokens.equals(tokens);
	}
}
