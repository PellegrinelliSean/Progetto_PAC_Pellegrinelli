package com.ourbooks.code.domain.grafo;

import org.springframework.stereotype.Service;

/**
 * The Classe ServizioGrafo. Servizio rest.
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

}
