package com.ourbooks.code.domain.grafo;

import java.util.LinkedList;

/**
 * La Classe Percorso. Utilizzata nell'algoritmo di Dijkstra.
 */
public class Percorso {
	/** I nodi del percorso, dal venditore fino all'ultimo utente del percorso eccetto l'acquirente. */
	private LinkedList<String> nodi;
	
	/** Le distanze in km tra i nodi del percorso. In posizione i è salvata la distanza tra il nodo
	 *  i ed il nodo i+1. In ultima posizione è salvata la distanza tra l'ultimo nodo nella lista nodi
	 *  e il nodo acquirente. */
	private LinkedList<Double> distanze;

	/** La distanza totale del percorso. */
	private double dist_totale;
	
	/** Numero di passi del percorso. */
	private int passi;
	
	/**
	 * Istanzia un nuovo percorso.
	 */
	public Percorso() {
		nodi = new LinkedList<String>();
		distanze = new LinkedList<Double>();
		dist_totale = Double.MAX_VALUE;
		passi = 0;
	}

	/**
	 * Get della distanza totale totale.
	 *
	 * @return la distanza totale del percorso
	 */
	public double getDist_totale() {
		return dist_totale;
	}
	
	/**
	 * Set della distanza totale del percorso.
	 *
	 * @param dist_totale la nuova distanza totale
	 */
	public void setDist_totale(double dist_totale) {
		this.dist_totale = dist_totale;
	}
	
	/**
	 * Get del numero di passi.
	 *
	 * @return il numero di passi
	 */
	public int getPassi() {
		return passi;
	}

	/**
	 * Incrementa il numero di passi del percorso.
	 *
	 * @param passi il numero di passi da aggiungere al totale
	 */
	public void setPassi(int passi) {
		this.passi = passi;
	}
	
	/**
	 * Get della lista di nodi.
	 *
	 * @return lista di nodi
	 */
	public LinkedList<String> getNodi() {
		return nodi;
	}

	/**
	 * cancella la lista di nodi e inserisci gli elementi della lista di nodi passata.
	 *
	 * @param nodi lista di nodi da aggiungere
	 */
	public void setNodi(LinkedList<String> nodi) {
		this.nodi.clear();
		this.nodi.addAll(nodi);
	}

	/**
	 * Aggiunta di un nodo al percorso.
	 *
	 * @param nodo il nodo da aggiungere al percorso
	 */
	public void addNodo(String nodo) {
		nodi.addFirst(nodo);
	}
	
	/**
	 * Get della lista di distanze.
	 *
	 * @return lista di distanze
	 */
	public LinkedList<Double> getDistanze() {
		return distanze;
	}

	/**
	 * cancella la lista di distanze e inserisci gli elementi della lista passata.
	 *
	 * @param distanze lista di distanze da aggiungere
	 */
	public void setDistanze(LinkedList<Double> distanze) {
		this.distanze.clear();
		this.distanze.addAll(distanze);
	}
	
	/**
	 * Aggiunta di una distanza tra nodi al percorso.
	 *
	 * @param distanza la distanza tra due nodi
	 */
	public void addDistanza(double distanza) {
		distanze.addFirst(distanza);
	}
	
	/**
	 * Porta il percorso alle condizioni iniziali per l'esecuzione dell'algoritmo di Dijkstra
	 */
	public void reset() {
		nodi.clear();
		distanze.clear();
		dist_totale = Double.MAX_VALUE;
		passi = 0;
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
		Percorso other = (Percorso)obj;
		
		if(other.distanze.size() != distanze.size()) return false;
		
		for(int i = 0; i < distanze.size(); i++) {
			if(Math.abs(other.distanze.get(i) - distanze.get(i)) > 1)
				return false;
		}
		return other.nodi.equals(nodi) && Math.abs(other.dist_totale - dist_totale) <= 1 && other.passi == passi;
	}
}
