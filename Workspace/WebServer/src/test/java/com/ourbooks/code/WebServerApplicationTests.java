package com.ourbooks.code;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.ourbooks.code.domain.utenti.DtoUtente;
import com.ourbooks.code.domain.utenti.ServizioUtenti;
import com.ourbooks.code.domain.utenti.Utente;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class WebServerApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	//ServizioUtenti usato solo per eliminare dal database i dati inseriti nello
	//stesso durante i test
	@Autowired
	private ServizioUtenti servizioU; 
	
	@Test
	public void testRegistrazioneLogin() {
		String url_signup = "http://localhost:8080/signup";
		String url_login = "http://localhost:8080/login";
		
		//REGISTRA UTENTE 1
		//creazione dto utente 1
		DtoUtente u_dto = new DtoUtente();
		u_dto.setEmail("utente1@gmail.com");
		u_dto.setPassword("psw_u1");
		u_dto.setLat(45.44);
		u_dto.setLon(10.14);
		u_dto.setMaxDist(10);
		u_dto.setLibriDesiderati(new String[]{"It", "Se questo è un uomo"});
		//creazione richiesta http
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<DtoUtente> httpEntity_signup = new HttpEntity<>(u_dto, headers);
		//invio richiesta
		ResponseEntity<String> response = restTemplate.exchange(url_signup, HttpMethod.POST, httpEntity_signup, String.class);
		//verifica correttezza risposta
		assertEquals("200 OK", response.getStatusCode().toString());
		
		//REGISTRAZIONE UTENTE CON STESSA EMAIL
		response = restTemplate.exchange(url_signup, HttpMethod.POST, httpEntity_signup, String.class);
		assertEquals("400 BAD_REQUEST", response.getStatusCode().toString());
		
		//LOGIN UTENTE 1
		//creazione credenziali
		Map<String, String> credenziali = new HashMap<String, String>();
		credenziali.put("email", u_dto.getEmail());
		credenziali.put("password", u_dto.getPassword());
		//creazione richiesta http
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Map<String, String>> httpEntity_login = new HttpEntity<>(credenziali, headers);
		//invio richiesta
		Utente u = restTemplate.postForObject(url_login, httpEntity_login, Utente.class);
		//verifica che l'utente ritornato abbia le stesse info di u1_dto
		assertEquals(u_dto.getEmail(), u.getEmail());
		assertEquals(u_dto.getPassword(), u.getPassword());
		assertEquals(u_dto.getLat(), u.getLat(), 0);
		assertEquals(u_dto.getLon(), u.getLon(), 0);
		assertEquals(u_dto.getMaxDist(), u.getMaxDist(), 0);
		assertArrayEquals(new String[]{u_dto.getLibriDesiderati()[0], u_dto.getLibriDesiderati()[1], null}, u.getLibriDesiderati());
		assertEquals(0, u.getnToken());
		
		//LOGIN UTENTE 1 CREDENZIALI ERRATE
		credenziali = new HashMap<String, String>();
		credenziali.put("email", "errata@gmail.com");
		credenziali.put("password", u_dto.getPassword());
		httpEntity_login = new HttpEntity<>(credenziali, headers);
		response = restTemplate.exchange(url_login, HttpMethod.POST, httpEntity_login, String.class);
		assertEquals("400 BAD_REQUEST", response.getStatusCode().toString());
		
		credenziali = new HashMap<String, String>();
		credenziali.put("email", u_dto.getEmail());
		credenziali.put("password", "psw_err");
		httpEntity_login = new HttpEntity<>(credenziali, headers);
		response = restTemplate.exchange(url_login, HttpMethod.POST, httpEntity_login, String.class);
		assertEquals("400 BAD_REQUEST", response.getStatusCode().toString());
		
		//CANCELLO UTENTE 1
		servizioU.eliminaAccount(u.getId());
	}

}
