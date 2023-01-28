package com.ourbooks.code.domain.grafo;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

public class GrafoRaggiungibilita {
	private static GrafoRaggiungibilita istanzaGrafo;
	private DefaultDirectedWeightedGraph<String, DefaultEdge> grafo;
	
	public GrafoRaggiungibilita() {
		grafo = new DefaultDirectedWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
	}
	
	public static GrafoRaggiungibilita getIstanza() {
		if (istanzaGrafo == null)
			istanzaGrafo = new GrafoRaggiungibilita();
		return istanzaGrafo;
	}
	
	
	
}
