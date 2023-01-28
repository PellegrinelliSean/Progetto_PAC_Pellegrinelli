package com.ourbooks.code.domain.utenti;

import java.util.HashSet;

public class RepositoryUtenti {
	private HashSet<Utente> listaUtenti = new HashSet<Utente>();
	
	public Utente save(Utente utente) {
		this.listaUtenti.add(utente);
		return utente;
	}

	public HashSet<Utente> findAll() {
		return listaUtenti;
	}
}
