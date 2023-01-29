package com.ourbooks.code;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.ourbooks.code.domain.utenti.CondLibro;
import com.ourbooks.code.domain.utenti.ServizioLibri;
import com.ourbooks.code.domain.utenti.ServizioUtenti;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebServerApplicationTests {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	ServizioUtenti servizioU;
	@Autowired
	ServizioLibri servizioL;
	
	@Test
	public void temptest() {
//		servizioU.creaAccount("nico.pellenp@gmail.com", "passwordprova", 35.5, 4.6, 10, "Lo Hobbit", "V per Vendetta");
//		String id = servizioU.login("nico.pellenp@gmail.com", "passwordprova");
//		servizioL.aggiungiLibro(id, "Tre Joker", 250, 2020, CondLibro.BUONE, true);
//		servizioL.aggiungiLibro(id, "Chtulhu", 400, 2018, CondLibro.OTTIME, false);
//		servizioU.creaAccount("sean.pellegrinelli@gmail.com", "passwordprova", 12.5, -34.6, 5, "V per Vendetta", "Kingdome Come", "The Withcer", "Java concurrency in practice");
//		id = servizioU.login("sean.pellegrinelli@gmail.com", "passwordprova");
//		servizioL.aggiungiLibro(id, "Il signore degli anelli", 1200, 2015, CondLibro.BUONE, false);
	}

}
