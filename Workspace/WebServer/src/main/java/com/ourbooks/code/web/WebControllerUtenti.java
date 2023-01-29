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

@RestController
public class WebControllerUtenti {
	
	@Autowired 
	private ServizioUtenti servizioUtenti;
	
	@Autowired 
	private ServizioLibri servizioLibri;
	
	@PostMapping("/signup")
	public String signup(@RequestBody DtoUtente dto) {
		boolean successo = servizioUtenti.creaAccount(dto.getEmail(), dto.getPassword(), dto.getLat(),
				dto.getLon(), dto.getMaxDist(), dto.getLibriDesiderati());
		if (successo)
			return "Registrazione avvenuta con successo";
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail già presente");
	}
	
	@PostMapping("/login")
	public Utente login(@RequestBody Map<String, String> json) {
		Utente u = servizioUtenti.login(json.get("email"), json.get("password"));
		if (u != null)
			return u;
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail o password errati");
	}
	
	@PutMapping("/utenti/{idUtente}")
	public void addLibro(@RequestBody DtoLibro dto, @PathVariable String idUtente) {
		servizioLibri.aggiungiLibro(idUtente, dto.getTitolo(), dto.getNumPagine(), dto.getYearPub(),
				dto.getCondizioni(), dto.isIllustrato());
	}

}
