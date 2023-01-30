package com.ourbooks.code.domain.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourbooks.code.domain.grafo.ServizioGrafo;

/**
 * La Classe ServizioUtenti. Servizio rest.
 */
@Service
public class ServizioUtenti {
	
	/** Il servizio grafo. */
	@Autowired
	private ServizioGrafo servizioGrafo;
	
	/** La repository. */
	@Autowired
	private RepositoryUtenti repository;
	
	/**
	 * Crea account.
	 *
	 * @param email la email
	 * @param password la password
	 * @param lat la latitudine
	 * @param lon the longitudine
	 * @param maxDist massima distanza percorribile
	 * @param libriDesiderati i titoli dei libri desiderati
	 * @return true, se l'account e' stato creato con successo
	 */
	public boolean creaAccount(String email, String password, double lat, double lon, double maxDist, String...libriDesiderati) {
		//Verifica situazione di errore: utente già registrato
		if (repository.findItemByEmail(email) != null)
			return false;
		Utente u = new Utente(email, password, lat, lon, maxDist, libriDesiderati);
		repository.save(u);
		servizioGrafo.aggiungiNodo(u.getId(), lat, lon, maxDist);
		return true;
	}
	
	/**
	 * Login.
	 *
	 * @param email la email
	 * @param password la password
	 * @return l'utente, null se non presente
	 */
	public Utente login(String email, String password) {
		Utente u = repository.findItemByEmail(email);
			if (u == null || !password.equals(u.getPassword()))
				return null; //Sitazione di errore: emial non presente o password errata
		return u;
	}
	
	/**
	 * Get di tutti gli utenti.
	 *
	 * @return la lista di tutti utenti
	 */
	public List<Utente> getAllUtenti(){
		return repository.findAll();
	}
	
	/**
	 * Eliminazione di un account.
	 *
	 * @param userId l'id dell'utente
	 */
	public void eliminaAccount(String userId) {
		repository.delete(repository.findItemById(userId));
	}
}
