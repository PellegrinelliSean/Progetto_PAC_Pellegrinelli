package com.ourbooks.code.domain.grafo;

import java.util.Set;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

public class GrafoRaggiungibilita {
	private static GrafoRaggiungibilita istanzaGrafo;
	private DefaultDirectedWeightedGraph<VerticeUtente, DefaultEdge> grafo;
	
	public GrafoRaggiungibilita() {
		grafo = new DefaultDirectedWeightedGraph<VerticeUtente, DefaultEdge>(DefaultEdge.class);
	}
	
	public static GrafoRaggiungibilita getIstanza() {
		if (istanzaGrafo == null)
			istanzaGrafo = new GrafoRaggiungibilita();
		return istanzaGrafo;
	}
	
	public Set<VerticeUtente> vertexSet() {
		return grafo.vertexSet();
	}
	
	public void addVertice(VerticeUtente vu) {
		grafo.addVertex(vu);
	}
	
	public void addArco(VerticeUtente vu1, VerticeUtente vu2, double dist) {
		grafo.addEdge(vu1, vu2);
		grafo.setEdgeWeight(vu1, vu2, dist);
	}
}
