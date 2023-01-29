package com.ourbooks.code.domain.utenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourbooks.code.domain.grafo.ServizioGrafo;

@Service
public class ServizioUtenti {
	@Autowired
	private ServizioGrafo servizioGrafo = new ServizioGrafo();
	private RepositoryUtenti repository = RepositoryUtenti.getIstanza();
	
	public boolean creaAccount(String email, String password, double lat, double lon, double maxDist, String...libriPref) {
		//Verifica situazione di errore: utente già registrato
		if (repository.findUtenteByEmail(email) != null)
			return false;
		Utente u = new Utente(email, password, lat, lon, maxDist, libriPref);
		repository.save(u);
		servizioGrafo.aggiungiNodo(u.getId(), lat, lon, maxDist);
		return true;
	}
	
	public String login(String email, String password) {
		Utente u = repository.findUtenteByEmail(email);
			if (u == null || !password.equals(u.getPassword()))
				return "ERR"; //Sitazione di errore: emial nono presente o password errata
		return u.getId();
	}
	
}
