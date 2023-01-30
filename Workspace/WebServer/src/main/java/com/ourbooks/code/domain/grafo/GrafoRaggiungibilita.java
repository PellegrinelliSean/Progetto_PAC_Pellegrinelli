package com.ourbooks.code.domain.grafo;

import java.util.Optional;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * La Classe GrafoRaggiungibilita. Per la gestione del grafo raggiungibilita utenti. Singleton.
 */
public class GrafoRaggiungibilita {
	
	/** L'unica istanza della classe. */
	private static GrafoRaggiungibilita istanzaGrafo;
	
	/** Il grafo. I vertici sono della classe VerticeUtente */
	private DefaultDirectedWeightedGraph<VerticeUtente, DefaultWeightedEdge> grafo;
	
	/**
	 * Istanzia il grafo raggiungibilita.
	 */
	private GrafoRaggiungibilita() {
		grafo = new DefaultDirectedWeightedGraph<VerticeUtente, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}
	
	/**
	 * Get dell'istanza. Se null prima la istanzia.
	 *
	 * @return l'istanza
	 */
	public static GrafoRaggiungibilita getIstanza() {
		if (istanzaGrafo == null)
			istanzaGrafo = new GrafoRaggiungibilita();
		return istanzaGrafo;
	}
	
	/**
	 * Get dei vertici del grafo
	 *
	 * @return the il set dei vertici del grafo
	 */
	public Set<VerticeUtente> vertexSet() {
		return grafo.vertexSet();
	}
	
	/**
	 * Aggiunta di un vertice.
	 *
	 * @param vu il vertice da aggiungere
	 */
	public void addVertice(VerticeUtente vu) {
		grafo.addVertex(vu);
	}
	
	/**
	 * Aggiunta di un arco.
	 *
	 * @param vu1 il vertice da cui parte l'arco
	 * @param vu2 il vertice in cui entra l'arco
	 * @param dist il peso dell'arco
	 */
	public void addArco(VerticeUtente vu1, VerticeUtente vu2, double dist) {
		grafo.addEdge(vu1, vu2);
		grafo.setEdgeWeight(vu1, vu2, dist);
	}
	
	/**
	 * Rimozione di un vertice.
	 *
	 * @param vu il vertice da rimuovere
	 */
	public void removeVertice(VerticeUtente vu) {
		grafo.removeVertex(vu);
	}
	
	/**
	 * Restituisce un vertice.
	 *
	 * @param userId id del vertice da restituire
	 * @return il vertice con tale id
	 */
	public VerticeUtente getVerticeById(String userId) {
		Optional<VerticeUtente> vu_opt = grafo.vertexSet().stream().filter(v -> v.getId().equals(userId)).findFirst();
		return vu_opt.get();
	}
	
	/**
	 * Verifica l'esistenza di un arco tra due vertici.
	 *
	 * @param vu1 il vertice da cui parte l'arco
	 * @param vu2 il vertice in cui entra l'arco
	 * @return -1 se l'arco non esiste, il peso dell'arco se esiste
	 */
	public double verificaArco(VerticeUtente vu1, VerticeUtente vu2) {
		return grafo.containsEdge(vu1, vu2) ? grafo.getEdgeWeight(grafo.getEdge(vu1, vu2)) : -1;
	}

}
