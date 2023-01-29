package com.ourbooks.code.domain.utenti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourbooks.code.domain.grafo.ServizioGrafo;

@Service
public class ServizioUtenti {
	@Autowired
	private ServizioGrafo servizioGrafo;
	@Autowired
	private RepositoryUtenti repository;
	
	public boolean creaAccount(String email, String password, double lat, double lon, double maxDist, String...libriPref) {
		//Verifica situazione di errore: utente già registrato
		if (repository.findItemByEmail(email) != null)
			return false;
		Utente u = new Utente(email, password, lat, lon, maxDist, libriPref);
		repository.save(u);
		servizioGrafo.aggiungiNodo(u.getId(), lat, lon, maxDist);
		return true;
	}
	
	public Utente login(String email, String password) {
		Utente u = repository.findItemByEmail(email);
			if (u == null || !password.equals(u.getPassword()))
				return null; //Sitazione di errore: emial non presente o password errata
		return u;
	}
	
	public List<Utente> getAllUtenti(){
		return repository.findAll();
	}
}
