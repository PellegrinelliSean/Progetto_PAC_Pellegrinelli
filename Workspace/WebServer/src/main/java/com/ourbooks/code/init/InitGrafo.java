package com.ourbooks.code.init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ourbooks.code.domain.grafo.ServizioGrafo;
import com.ourbooks.code.domain.utenti.RepositoryUtenti;
import com.ourbooks.code.domain.utenti.Utente;

@Component
public class InitGrafo implements CommandLineRunner {
	
	@Autowired
	private ServizioGrafo servizioGrafo;
	@Autowired
	private RepositoryUtenti repository;
	
	public void run(String...args) {
		System.out.println("INIZIALIZZO");
		List<Utente> l = repository.findAll();
		for(Utente u : l)
			System.out.println(u.getEmail());
	}
}
