package com.ourbooks.code.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ourbooks.code.domain.utenti.DtoLibro;
import com.ourbooks.code.domain.utenti.DtoUtente;
import com.ourbooks.code.domain.utenti.ServizioLibri;
import com.ourbooks.code.domain.utenti.ServizioUtenti;
import com.ourbooks.code.domain.utenti.Utente;

/**
 * La Classe WebControllerUtenti. Implementa il rest controller.
 */
@RestController
public class WebControllerUtenti {
	
	/** Il servizio utenti. */
	@Autowired 
	private ServizioUtenti servizioUtenti;
	
	/** Il servizio libri. */
	@Autowired 
	private ServizioLibri servizioLibri;
	
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
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail o password errati");
	}
	
	/**
	 * Aggiunta di un libro a quelli disponibili per la vendita di un utente.
	 *
	 * @param dto il dto del libro
	 * @param idUtente l'id dell'utente
	 */
	@PutMapping("/utenti/{idUtente}")
	public void addLibro(@RequestBody DtoLibro dto, @PathVariable String idUtente) {
		servizioLibri.aggiungiLibro(idUtente, dto.getTitolo(), dto.getNumPagine(), dto.getYearPub(),
				dto.getCondizioni(), dto.isIllustrato());
	}

}
