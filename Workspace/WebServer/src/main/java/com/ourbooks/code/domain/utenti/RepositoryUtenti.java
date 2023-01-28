package com.ourbooks.code.domain.utenti;

import java.util.Collection;
import java.util.HashMap;

public class RepositoryUtenti {
	private static RepositoryUtenti istanzaRepository;
	private HashMap<String, Utente> listaUtenti;
	
	public RepositoryUtenti() {
		listaUtenti = new HashMap<String, Utente>();
	}
	
	public static RepositoryUtenti getIstanza() {
		if (istanzaRepository == null)
			istanzaRepository = new RepositoryUtenti();
		return istanzaRepository;
	}
	
	public Utente save(Utente utente) {
		this.listaUtenti.put(utente.getId(), utente);
		return utente;
	}

	public Collection<Utente> findAll() {
		return listaUtenti.values();
	}
	
	public Utente findUtenteById(String userId) {
		return listaUtenti.get(userId);
	}
	
	public Utente findUtenteByEmail(String email) {
		for (Utente u : this.findAll())
			if (email.equals(u.getEmail()))
				return u;
		return null;
	}
}
