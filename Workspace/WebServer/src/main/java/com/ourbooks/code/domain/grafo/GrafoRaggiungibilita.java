package com.ourbooks.code.domain.grafo;

import java.util.Set;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * La Classe GrafoRaggiungibilita. Per la gestione del grafo raggiungibilita utenti. Singleton.
 */
public class GrafoRaggiungibilita {
	
	/** L'unica istanza della classe. */
	private static GrafoRaggiungibilita istanzaGrafo;
	
	/** Il grafo. I vertici sono della classe VerticeUtente */
	private DefaultDirectedWeightedGraph<VerticeUtente, DefaultEdge> grafo;
	
	/**
	 * Istanzia il grafo raggiungibilita.
	 */
	public GrafoRaggiungibilita() {
		grafo = new DefaultDirectedWeightedGraph<VerticeUtente, DefaultEdge>(DefaultEdge.class);
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
}
