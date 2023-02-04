package com.ourbooks.code.web;

import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ourbooks.code.domain.account.DtoLibro;
import com.ourbooks.code.domain.account.DtoUtente;
import com.ourbooks.code.domain.account.ServizioLibri;
import com.ourbooks.code.domain.account.ServizioUtenti;
import com.ourbooks.code.domain.acquisto.ServizioAcquisto;
import com.ourbooks.code.domain.account.Utente;
import com.ourbooks.code.domain.acquisto.SpecificheAcquisto;

/**
 * La Classe WebController. Implementa il rest controller.
 */
@RestController
public class WebController {
	
	/** Il servizio utenti. */
	@Autowired 
	private ServizioUtenti servizioUtenti;
	
	/** Il servizio libri. */
	@Autowired 
	private ServizioLibri servizioLibri;
	
	/** Il servizio acquisto. */
	@Autowired 
	private ServizioAcquisto servizioA;
	
	/**
	 * Signup di un nuovo utente.
	 *
	 * @param dto il dto dell'utente
	 * @return la stringa di risposta
	 */
	@PostMapping("/signup")
	public String signup(@RequestBody DtoUtente dto) {
		boolean successo = servizioUtenti.creaAccount(dto.getEmail(), dto.getPassword(), dto.getLat(),
				dto.getLon(), dto.getMaxDist(), dto.getLibriDesiderati());
		if (successo)
			return "Registrazione avvenuta con successo";
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail già presente");
	}
	
	/**
	 * Login di un utente.
	 *
	 * @param json il documento json contenente email e password
	 * @return l'utente
	 */
	@PostMapping("/login")
	public Utente login(@RequestBody Map<String, String> json) {
		Utente u = servizioUtenti.login(json.get("email"), json.get("password"));
		if (u != null)
			return u;
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail o password errati");
	}
	
	/**
	 * Aggiunta di un libro a quelli disponibili per la vendita di un utente.
	 *
	 * @param dto il dto del libro
	 * @param idUtente l'id dell'utente
	 * @return l'utente a cui è stato aggiunto il libro
	 */
	@PutMapping("/utenti/{idUtente}")
	public Utente addLibro(@RequestBody DtoLibro dto, @PathVariable String idUtente) {
		return servizioLibri.aggiungiLibro(idUtente, dto.getTitolo(), dto.getNumPagine(), dto.getYearPub(),
				dto.getCondizioni(), dto.isIllustrato());
	}
	
	/**
	 * Ritorna la lista di libri acquistabili dall'utente
	 *
	 * @param idUtente l'id dell'utente
	 * @return la lista di libri acquistabili coi relativi prezzi in token
	 */
	@GetMapping("/libri-aquistabili/{idUtente}")
	public LinkedList<SpecificheAcquisto> getLibriAcquistabili(@PathVariable String idUtente) {
		return servizioA.getLibriAcquistabili(idUtente);
	}
}
