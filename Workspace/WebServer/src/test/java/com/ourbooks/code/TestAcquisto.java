package com.ourbooks.code;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.ourbooks.code.domain.account.CondLibro;
import com.ourbooks.code.domain.account.Libro;
import com.ourbooks.code.domain.account.ServizioLibri;
import com.ourbooks.code.domain.account.ServizioUtenti;
import com.ourbooks.code.domain.account.Utente;
import com.ourbooks.code.domain.acquisto.SpecificheAcquisto;
import com.ourbooks.code.domain.grafo.ServizioGrafo;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class TestAcquisto {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	//ServizioUtenti usato per fare signup, login e cancellazione degli utenti
	//direttamente senza passare per le richieste rest (testate in altre classi di test)
	@Autowired
	private ServizioUtenti servizioU; 
	
	//ServizioUtenti usato per aggiungere libri agli utenti senza passare per le richieste rest
	@Autowired
	private ServizioLibri servizioL; 
	
	//ServizioGrafo usato per svuotare il grafo in modo che i nodi di test siano gli unici presenti
	@Autowired
	private ServizioGrafo servizioG; 
	
	@Test
	public void testListaAcquistabili() {
		//svuoto il grafo di raggiungibilità
		servizioG.svuotaGrafo();
		
		//REGISTRA 3 UTENTI
		servizioU.creaAccount("email1@yahoo.com", "psw1", 0.0, 0.0, 0, "It");
		servizioU.creaAccount("email2@yahoo.com", "psw2", 0.1, 0.0, 12);//circa 11.1km da u1
		servizioU.creaAccount("email3@yahoo.com", "psw3", 0.1, 0.15, 17);//circa 16.68km da u2
		
		//OTTENGO I TRE UTENTI (direttamente attraveso servizioU, senza richiesta rest)
		Utente u1 = servizioU.login("email1@yahoo.com", "psw1");
		Utente u2 = servizioU.login("email2@yahoo.com", "psw2");
		Utente u3 = servizioU.login("email3@yahoo.com", "psw3");
		
		//AGGIUNTA LIBRI UTENTE 1
		u1 = servizioL.aggiungiLibro(u1.getId(), "300", 100, 2020, CondLibro.BUONE, true);
		//AGGIUNTA LIBRI UTENTE 2
		u2 = servizioL.aggiungiLibro(u2.getId(), "Il Miglio Verde", 800, 2023, CondLibro.CATTIVE, false);
		u2 = servizioL.aggiungiLibro(u2.getId(), "Shining", 1140, 2000, CondLibro.PESSIME, false);
		//AGGIUNTA LIBRI UTENTE 3
		u3 = servizioL.aggiungiLibro(u3.getId(), "Il Signore degli Anelli", 3000, 2018, CondLibro.OTTIME, true);
		
		//RICHIESTA REST
		String url = "http://localhost:8080/utenti/";
		ResponseEntity<LinkedList<SpecificheAcquisto>> response = restTemplate.exchange(url + u1.getId(),
		                    HttpMethod.GET, null, new ParameterizedTypeReference<LinkedList<SpecificheAcquisto>>(){});
		LinkedList<SpecificheAcquisto> listaLibri = response.getBody();
		
		//creo le tre specifiche acquisto i aspetto di trovare e verifico che siano in listaLibri
		SpecificheAcquisto sa;
		Libro l;
		LinkedList<String> utenti;
		LinkedList<Integer> tokens;
		//primo libro utente 2
		l = u2.getLibri().get(0); //"Il Miglio Verde" (i libri vengono aggiunti in coda alla lista di libri dell'utente)
		utenti = new LinkedList<String>();
		utenti.addFirst(u2.getId());
		tokens = new LinkedList<Integer>();
		/*
		 * costo libro: 800 * 0.8 * (1 + (50 - (2023 -2023)) / 100 = 960
		 * costo trasporto: 100 + 11.1 * 20 = 322
		 */
		tokens.addFirst(960 + 322);
		sa = new SpecificheAcquisto(l, utenti, tokens);
		assertTrue(listaLibri.contains(sa));
		//secondo libro utente 2
		l = u2.getLibri().get(1); //"Shining"
		utenti = new LinkedList<String>();
		utenti.addFirst(u2.getId());
		tokens = new LinkedList<Integer>();
		/*
		 * costo libro: 1140 * 0.5 * (1 + (50 - (2023 -2000)) / 100 = 724
		 * costo trasporto: 100 + 11.1 * 20 = 322
		 */
		tokens.addFirst(724 + 322);
		sa = new SpecificheAcquisto(l, utenti, tokens);
		assertTrue(listaLibri.contains(sa));
		//libro utente 3
		l = u3.getLibri().get(0); //"Il Signore degli Anelli"
		utenti = new LinkedList<String>();
		utenti.addFirst(u2.getId());
		utenti.addFirst(u3.getId());
		tokens = new LinkedList<Integer>();
		/*
		 * costo libro: (3000 + 500) * 1.5 * (1 + (50 - (2023 -2018)) / 100 = 7613
		 * costo trasporto acquirente: 100 + 16.68 * 20 = 434
		 * costo trasporto intermediario: 100 + 11.1 * 20 = 322
		 */
		tokens.addFirst(322);
		tokens.addFirst(7613 + 434);
		sa = new SpecificheAcquisto(l, utenti, tokens);
		assertTrue(listaLibri.contains(sa));
		
		//Verifico che non ci siano altri libri disponibili oltre a questi 3
		assertEquals(3, listaLibri.size());
		
		//CANCELLO GLI UTENTI DAL DATABASE
		servizioU.eliminaAccount(u1.getId());
		servizioU.eliminaAccount(u2.getId());
		servizioU.eliminaAccount(u3.getId());
	}
}
