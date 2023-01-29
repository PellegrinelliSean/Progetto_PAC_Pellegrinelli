package com.ourbooks.code.init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ourbooks.code.domain.grafo.ServizioGrafo;
import com.ourbooks.code.domain.utenti.ServizioUtenti;
import com.ourbooks.code.domain.utenti.Utente;

@Component
public class InitGrafo implements CommandLineRunner {
	
	@Autowired
	private ServizioGrafo servizioGrafo;
	@Autowired
	private ServizioUtenti servizioUtenti;
	
	public void run(String...args) {
		List<Utente> utenti = servizioUtenti.getAllUtenti();
		for(Utente u : utenti)
			servizioGrafo.aggiungiNodo(u.getId(), u.getLat(), u.getLon(), u.getMaxDist());
	}
}
