package com.ourbooks.code.init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ourbooks.code.domain.account.ServizioUtenti;
import com.ourbooks.code.domain.account.Utente;
import com.ourbooks.code.domain.grafo.ServizioGrafo;

/**
 * The Class InitGrafo. Per l'inizializzazione del grafo.
 */
@Component
public class InitGrafo implements CommandLineRunner {
	
	/** Il servizio grafo. */
	@Autowired
	private ServizioGrafo servizioGrafo;
	
	/** Il servizio utenti. */
	@Autowired
	private ServizioUtenti servizioUtenti;
	
	/**
	 * Run.
	 *
	 * @param args gli arguments
	 */
	public void run(String...args) {
		List<Utente> utenti = servizioUtenti.getAllUtenti();
		for(Utente u : utenti)
			servizioGrafo.aggiungiNodo(u.getId(), u.getLat(), u.getLon(), u.getMaxDist());
	}
}
