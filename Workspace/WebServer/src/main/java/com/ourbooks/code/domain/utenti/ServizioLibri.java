package com.ourbooks.code.domain.utenti;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class ServizioLibri {
	private RepositoryUtenti repository = RepositoryUtenti.getIstanza();
	
	public void aggiungiLibro(String userId, String titolo, int numPagine, Date dataPub, CondLibro condizioni, boolean illustrato) {
		Utente u = repository.findUtenteById(userId);
		u.addLibro(new Libro(titolo, numPagine, dataPub, condizioni, illustrato));
	}
	
}
