package com.ourbooks.code.domain.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * La Classe ServizioLibri. Servizio rest.
 */
@Service
public class ServizioLibri {
	
	/** La repository degli utenti. */
	@Autowired
	private RepositoryUtenti repository;
	
	/**
	 * Aggiungi libro.
	 *
	 * @param userId l'id dell'utente
	 * @param titolo il titolo
	 * @param numPagine il numero di
	 * @param yearPub l'anno di pubblicazione
	 * @param condizioni le condizioni
	 * @param illustrato se e' illustrato
	 * @return l'utente a cui è stato aggiunto il libro
	 */
	public Utente aggiungiLibro(String userId, String titolo, int numPagine, int yearPub, CondLibro condizioni, boolean illustrato) {
		Utente u = repository.findItemById(userId);
		u.addLibro(new Libro(titolo, numPagine, yearPub, condizioni, illustrato));
		repository.save(u);
		return u;
	}
	
}
