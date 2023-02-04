package com.ourbooks.code;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.ourbooks.code.domain.account.DtoUtente;
import com.ourbooks.code.domain.account.ServizioUtenti;
import com.ourbooks.code.domain.account.Utente;
import com.ourbooks.code.domain.grafo.GrafoRaggiungibilita;
import com.ourbooks.code.domain.grafo.Percorso;
import com.ourbooks.code.domain.grafo.ServizioGrafo;
import com.ourbooks.code.domain.grafo.VerticeUtente;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class TestGrafo {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	//ServizioUtenti usato per eliminare dal database i dati inseriti nello stesso durante i test
	@Autowired
	private ServizioUtenti servizioU; 
	
	@Autowired
	private ServizioGrafo servizioG; 
	
	@Test
	public void testGrafoRaggiungibilità() {
		String url_signup = "http://localhost:8080/signup";
		
		//REGISTRA UTENTE 1
		//creazione dto utente
		DtoUtente u_dto1 = new DtoUtente();
		u_dto1.setEmail("utente1@gmail.com");
		u_dto1.setPassword("psw_u1");
		u_dto1.setLat(45.892687);
		u_dto1.setLon(9.983386);
		u_dto1.setMaxDist(25);
		u_dto1.setLibriDesiderati(new String[]{"It", "Se questo è un uomo"});
		//creazione richiesta http
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<DtoUtente> httpEntity_signup = new HttpEntity<>(u_dto1, headers);
		//invio richiesta
		restTemplate.exchange(url_signup, HttpMethod.POST, httpEntity_signup, String.class);
		
		//REGISTRA UTENTE 2
		//creazione dto utente
		DtoUtente u_dto2 = new DtoUtente();
		u_dto2.setEmail("utente2@gmail.com");
		u_dto2.setPassword("psw_u2");
		u_dto2.setLat(45.759481);
		u_dto2.setLon(9.796503);
		u_dto2.setMaxDist(5);
		u_dto2.setLibriDesiderati(new String[]{"It", "Shining", "I promessi sposi"});
		//creazione richiesta http
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		httpEntity_signup = new HttpEntity<>(u_dto2, headers);
		//invio richiesta
		restTemplate.exchange(url_signup, HttpMethod.POST, httpEntity_signup, String.class);
		
		//REGISTRA UTENTE 3
		//creazione dto utente
		DtoUtente u_dto3 = new DtoUtente();
		u_dto3.setEmail("utente3@gmail.com");
		u_dto3.setPassword("psw_u3");
		u_dto3.setLat(45.748222);
		u_dto3.setLon(9.842196);
		u_dto3.setMaxDist(10);
		u_dto3.setLibriDesiderati(new String[]{});
		//creazione richiesta http
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		httpEntity_signup = new HttpEntity<>(u_dto3, headers);
		//invio richiesta
		restTemplate.exchange(url_signup, HttpMethod.POST, httpEntity_signup, String.class);
		
		//OTTENGO I TRE UTENTI (direttamente attraveso servizioU, senza richiesta rest)
		Utente u1 = servizioU.login(u_dto1.getEmail(), u_dto1.getPassword());
		Utente u2 = servizioU.login(u_dto2.getEmail(), u_dto2.getPassword());
		Utente u3 = servizioU.login(u_dto3.getEmail(), u_dto3.getPassword());
		
		//OTTENGO I TRE CORRISPONDENTI VERTICI
		GrafoRaggiungibilita grafo = GrafoRaggiungibilita.getIstanza();
		VerticeUtente vu1 = grafo.getVerticeById(u1.getId());
		VerticeUtente vu2 = grafo.getVerticeById(u2.getId());
		VerticeUtente vu3 = grafo.getVerticeById(u3.getId());
		
		//VERIFICO LE CONNESSIONI TRA I TRE VERTICI
		/*
		 * Gli archi dovrebbero essere (in base alle coordinate e le massime distanze
		 * impostate) i seguenti:
		 * vu2 -> vu1
		 * vu3 -> vu1
		 * vu2 -> vu3
		 * vu3 -> vu2
		 */
		//mi aspetto -1 se non c'e l'arco, la distanza tra i nodi altrimenti
		assertEquals(-1, grafo.verificaArco(vu1, vu2), 0);
		assertEquals(20.71 , grafo.verificaArco(vu2, vu1), 0.1);
		assertEquals(-1, grafo.verificaArco(vu1, vu3), 0);
		assertEquals(19.44, grafo.verificaArco(vu3, vu1), 0.1);
		assertEquals(3.76 , grafo.verificaArco(vu2, vu3), 0.1);
		assertEquals(3.76 , grafo.verificaArco(vu3, vu2), 0.1);
		
		//CANCELLO GLI UTENTI DAL DATABASE
		servizioU.eliminaAccount(u1.getId());
		servizioU.eliminaAccount(u2.getId());
		servizioU.eliminaAccount(u3.getId());
	}
	
	@Test
	void testDijkstra() {
		/*
		 * I nodi vengono creati direttamente tramite il ServizioGrafo
		 * (non attraverso la registrazione di un utente tramite richiesta Rest,
		 * caso testato nel metodo testGrafoRaggiungibilità) così da poter impostare
		 * gli id e rendere più semplice la verifica
		 */
		//Creazione dei nodi
		servizioG.svuotaGrafo(); //elimino tutti i nodi del grafo così che non interferiscano col test
		servizioG.aggiungiNodo("1", 0, 0, 0);
		servizioG.aggiungiNodo("2", 0.1, 0, 12);
		servizioG.aggiungiNodo("3", 0.1, 0.1, 12);
		servizioG.aggiungiNodo("4", 0, 0.6, 57);
		servizioG.aggiungiNodo("5", -0.4, 0.3, 57);
		servizioG.aggiungiNodo("6", 0, 0.7, 12);
		/*
		 * Il grafo così creato ha i seguenti archi
		 * [1->2, 1->5, 2->3, 3->2, 3->4, 5->4, 4->5, 4->6, 6->4]
		 */
		
		//ottengo i percorsi minimi dal nodo 1
		LinkedList<Percorso> percorsi = servizioG.getPercorsiMinimi("1");
		
		//creo i quattro percorsi che mi aspetto di trovare e verifico che siano
		//contenuti nella lista "percorsi"
		Percorso p = new Percorso();
		LinkedList<String> nodi;
		LinkedList<Double> distanze;
		//Percorso da 1 a 2
		nodi = new LinkedList<String>();
		nodi.addFirst("2");
		distanze = new LinkedList<Double>();
		distanze.add(11.1);
		p.setNodi(nodi);
		p.setDistanze(distanze);
		p.setDist_totale(11.1);
		p.setPassi(1);
		assertTrue(percorsi.contains(p));
		
		//Percorso da 1 a 3
		nodi = new LinkedList<String>();
		nodi.addFirst("2");
		nodi.addFirst("3");
		distanze = new LinkedList<Double>();
		distanze.addFirst(11.1);
		distanze.addFirst(11.1);
		p.setNodi(nodi);
		p.setDistanze(distanze);
		p.setDist_totale(22.2);
		p.setPassi(2);
		assertTrue(percorsi.contains(p));
		
		//Percorso da 1 a 4
		nodi = new LinkedList<String>();
		nodi.addFirst("2");
		nodi.addFirst("3");
		nodi.addFirst("4");
		distanze = new LinkedList<Double>();
		distanze.addFirst(11.0);
		distanze.addFirst(11.0);
		distanze.addFirst(56.7);
		p.setNodi(nodi);
		p.setDistanze(distanze);
		p.setDist_totale(78.7);
		p.setPassi(3);
		assertTrue(percorsi.contains(p));
		
		//Percorso da 1 a 5
		nodi = new LinkedList<String>();
		nodi.addFirst("5");
		distanze = new LinkedList<Double>();
		distanze.add(55.6);
		p.setNodi(nodi);
		p.setDistanze(distanze);
		p.setDist_totale(55.6);
		p.setPassi(1);
		assertTrue(percorsi.contains(p));
		
		//Verifico che non ci siano altri percorsi oltre a questi 4
		assertEquals(4, percorsi.size());
	}
}
