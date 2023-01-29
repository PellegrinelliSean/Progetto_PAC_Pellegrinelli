package com.ourbooks.code.domain.utenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServizioLibri {
	@Autowired
	private RepositoryUtenti repository;
	
	public void aggiungiLibro(String userId, String titolo, int numPagine, int yearPub, CondLibro condizioni, boolean illustrato) {
		Utente u = repository.findItemById(userId);
		u.addLibro(new Libro(titolo, numPagine, yearPub, condizioni, illustrato));
		repository.save(u);
	}
	
}
