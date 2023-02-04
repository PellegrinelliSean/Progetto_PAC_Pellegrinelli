package com.ourbooks.code.domain.grafo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.stereotype.Service;

/**
 * La Classe ServizioGrafo. Servizio rest.
 */
@Service
public class ServizioGrafo {
	
	/** Il grafo raggiungibilita uteni. */
	private GrafoRaggiungibilita grafo = GrafoRaggiungibilita.getIstanza();
	
	/**
	 * Aggiungi nodo.
	 *
	 * @param userId l'id dell'utente
	 * @param lat la latitudine
	 * @param lon la longitudine
	 * @param maxDist la massima distanza percorribile
	 */
	public void aggiungiNodo(String userId, double lat, double lon, double maxDist) {
		VerticeUtente vu = new VerticeUtente(userId, lat, lon, maxDist);
		
		//aggiunta vertice al grafo
		grafo.addVertice(vu);
		
		//aggiunta archi incidenti sul nuovo nodo
		double dist;
		for(VerticeUtente vu2 : grafo.vertexSet()) {
			if(vu2.equals(vu)) continue;
			dist = distanzaUtenti(vu, vu2);
			if(vu.getMaxDist() >= dist) //il nuovo utente può consegnare libri all'i-esimo utente del ciclo
				grafo.addArco(vu2, vu, dist); 
			if(vu2.getMaxDist() >= dist) //l'i-esimo utente del ciclo può consegnare libri al nuovo utene
				grafo.addArco(vu, vu2, dist); 
		}
	}
	
	/**
	 * Elimina nodo.
	 *
	 * @param userId l'id dell'utente di cui eliminare il nodo
	 */
	public void eliminaNodo(String userId) {
		for(VerticeUtente vu : grafo.vertexSet()) {
			if(vu.getId().equals(userId)) {
				grafo.removeVertice(vu);
			}
		}
	}
	
	/**
	 * Resetta il grafo
	 */
	public void svuotaGrafo() {
		grafo.svuotaGrafo();
	}
	
	/**
	 * Modifica nodo.
	 *
	 * @param userId l'id dell'utente
	 * @param lat la latitudine
	 * @param lon la longitudine
	 * @param maxDist la massima distanza percorribile in km
	 */
	public void modificaNodo(String userId, double lat, double lon, double maxDist) {
		eliminaNodo(userId);
		aggiungiNodo(userId, lat, lon, maxDist);
	}
	
	/**
	 * Distanza tra due utenti. Metodo accessorio per il metodo aggiungiNodo
	 *
	 * @param vu1 il vertice del primo utente
	 * @param vu2 il vertice del secondo utente
	 * @return la distanza tra i due utenti
	 */
	private double distanzaUtenti(VerticeUtente vu1, VerticeUtente vu2) {
		double lat1 = Math.toRadians(vu1.getLat());
		double lon1 = Math.toRadians(vu1.getLon());
		double lat2 = Math.toRadians(vu2.getLat());
		double lon2 = Math.toRadians(vu2.getLon());

        double earthRadius = 6371.01; //Kilometers
        
        return earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
	}

	
	/**
	 * Applica l'algoritmo di Dijkstra sul grafo di raggiungibilità a partire dal nodo
	 * il cui id è specificato come parametro.
	 *
	 * @param idAcquirente l'id del nodo da cui partire
	 * @return la lista di percorsi (uno per ogni nodo raggiungibile dal nodo iniziale ad al massimo 3 passi)
	 */
	public LinkedList<Percorso> getPercorsiMinimi(String idAcquirente) {
		VerticeUtente acquirente = grafo.getVerticeById(idAcquirente);
		
		//Inizializzazione
		for(VerticeUtente vu : grafo.vertexSet())
			vu.getPercorso().reset();
		acquirente.getPercorso().setDist_totale(0);
		
		LinkedList<Percorso> percorsi = new LinkedList<Percorso>();
		Set<VerticeUtente> Q = new HashSet<VerticeUtente>();
		Q.addAll(grafo.vertexSet());
		
		VerticeUtente vu;
		VerticeUtente vu_adiacente;
		double pesoArco;
		while(Q.size() != 0) {
			vu = extractMin(Q);
			if(vu == null) break; //Visitati tutti i nodi a distanza massima pari a 3
			percorsi.addLast(vu.getPercorso());
			if(vu.getPercorso().getPassi() >= 3) continue; //Il nodo è già alla distanza massima, non rilasso i suoi archi
			for(DefaultWeightedEdge arco : grafo.outgoingEdgesOf(vu)) {
				pesoArco = grafo.getEdgeWeight(arco);
				vu_adiacente = grafo.getEdgeTarget(arco);
				//rilassamento
				if(vu_adiacente.getPercorso().getDist_totale() > vu.getPercorso().getDist_totale() + pesoArco) {
					vu_adiacente.getPercorso().setDist_totale(vu.getPercorso().getDist_totale() + pesoArco);
					vu_adiacente.getPercorso().setPassi(vu.getPercorso().getPassi() + 1);
					
					vu_adiacente.getPercorso().setNodi(vu.getPercorso().getNodi());
					vu_adiacente.getPercorso().addNodo(vu_adiacente.getId());
					
					vu_adiacente.getPercorso().setDistanze(vu.getPercorso().getDistanze());
					vu_adiacente.getPercorso().addDistanza(pesoArco);
				}
			}
			
		}
		percorsi.remove(0); //elimino il primo percorso, ovvero quello dall'acquirente a se stesso
		return percorsi;
	}
	
	/**
	 * Estrae da un Set di VerticeUtente quello col campo Dist_totale del campo Percorso più basso
	 *
	 * @param Q il set di VerticeUtente
	 * @return il VerticeUtente con distanza minima
	 */
	private VerticeUtente extractMin(Set<VerticeUtente> Q) {
		double min_dist = Double.MAX_VALUE;
		VerticeUtente min_vu = null;
		for(VerticeUtente vu : Q) {
			if(vu.getPercorso().getDist_totale() < min_dist) {
				min_dist = vu.getPercorso().getDist_totale();
				min_vu = vu;
			}
		}
		if(min_vu != null)
			Q.remove(min_vu);
		return min_vu;
	}
}
