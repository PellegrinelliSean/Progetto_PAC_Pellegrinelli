package com.ourbooks.code.domain.acquisto;

import java.time.Year;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ourbooks.code.domain.account.Libro;
import com.ourbooks.code.domain.account.RepositoryUtenti;
import com.ourbooks.code.domain.account.ServizioLibri;
import com.ourbooks.code.domain.account.Utente;
import com.ourbooks.code.domain.grafo.Percorso;
import com.ourbooks.code.domain.grafo.ServizioGrafo;

/**
 * La Classe ServizioAcquisto. Servizio rest.
 */
@Service
public class ServizioAcquisto {
	
	/** Il servizio relativo al grafo di raggiungibilità. */
	@Autowired
	private ServizioGrafo servizioG;
	
	/** Il servizio relativo ai libri. */
	@Autowired
	private ServizioLibri servizioL;
	
	/** La repository degli utenti. */
	@Autowired
	private RepositoryUtenti repository;
	
	/** L'oggetto per l'ivio della email. */
	@Autowired
    private JavaMailSender emailSender;
	
	/**
	 * Metodo che restituisce tutti i libri acquistabili da un utente ed il relativo costo in token,
	 * implementa una modifica all'algoritmo di Dijkstra
	 *
	 * @param idAcquirente l'id dell'utente acquirente
	 * @return la lista di SpecificheAcquisto, ovvero delle informazioni necessarie per effettuare 
	 * l'acquisto di ciascun libro disponibile per l'utente indicato
	 */
	public LinkedList<SpecificheAcquisto> getLibriAcquistabili(String idAcquirente) {
		LinkedList<Percorso> percorsi = servizioG.getPercorsiMinimi(idAcquirente);
		
		LinkedList<SpecificheAcquisto> result = new LinkedList<SpecificheAcquisto>();
		
		Utente u;
		SpecificheAcquisto sa;
		LinkedList<String> utenti_precorso;
		LinkedList<Integer> token_percorso;
		for(Percorso p : percorsi) {
			u = repository.findItemById(p.getNodi().getFirst());
			
			//calcolo dei token dovuti al trasporto del libro da assegnare agli utenti coinvolti
			utenti_precorso = p.getNodi();
			token_percorso = new LinkedList<Integer>();
			for(double distanza : p.getDistanze()) {
				token_percorso.addLast((int)Math.round(100 + distanza * 20));
			}
			
			//creazione e riempimento della specifica acquisto per ogni libro dell'i-esimo utente
			for(Libro l : u.getLibri()) {
				sa = new SpecificheAcquisto(l, utenti_precorso, token_percorso);

				//calcolo valore del libro in base alle pagine e se è illustrato
				double val_libro = l.getNumPagine() + (l.isIllustrato() ? 500 : 0);

				//aggunta del moltiplicatore per le condizoni
				double moltCondizioni = 1;
				switch(l.getCondizioni()) {
					case PESSIME:
						moltCondizioni = 0.5;
						break;
					case CATTIVE:
						moltCondizioni = 0.8;
						break;
					case BUONE:
						moltCondizioni = 1;
						break;
					case OTTIME:
						moltCondizioni = 1.5;
						break;
				}
				val_libro *= moltCondizioni;

				//aggunta del moltiplicatore per l'anno
				double moltAnno = 1;
				int annoCorrente = Year.now().getValue();
				if(annoCorrente - l.getYearPub() <= 50)
					moltAnno = 1 + (double)(50 - (annoCorrente - l.getYearPub())) / 100;
				val_libro *= moltAnno;

				//conversione dei token in valore intero
				int token_libro = (int)Math.round(val_libro); 
				
				sa.addTokenLibro(token_libro);
				
				result.add(sa);
			}
		}
		return result;
	}
	
	/**
	 * Viene finalizzata l'operazione di acquisto di un libro, rendendo persistenti le modifiche agli utenti,
	 * ossia la modifica dei token degli utenti coinvolti e la rimozione del libro al venditore 
	 *
	 * @param userId l'id dell'utente acquirente
	 * @param specifiche le specifiche dell'acquisto
	 * @return l'utente che ha acquistato il libro
	 */
	public Utente compraLibro(String userId, SpecificheAcquisto specifiche) {
		//Si ottiene l'utente acquirente
		Utente compratore = repository.findItemById(userId);
		int costoTotale = specifiche.getTokens().stream().mapToInt(Integer::intValue).sum();
		
		//Situazione di errore: l'acquirente non ha abbastanza token
		if (compratore.getnToken() < costoTotale)
			return null;
		
		compratore.setnToken(compratore.getnToken()-costoTotale);
		repository.save(compratore);
		
		//Si ottengono gli utenti da pagare, si aggiungono i token dovuti e al primo si toglie anche il libro
		Utente u;
		String idUtente;
		Utente u_ricevitore = compratore;
		SimpleMailMessage message;
		String text;
		for (int i = specifiche.getUtenti().size() - 1; i >= 0; i--) {
			idUtente = specifiche.getUtenti().get(i);
			u = repository.findItemById(idUtente);
			u.setnToken(u.getnToken() + specifiche.getTokens().get(i));
			repository.save(u);
			if (i == 0)
				servizioL.eliminaLibro(idUtente, specifiche.getLibro().getId());
			
			//invio email
			message = new SimpleMailMessage(); 
	        message.setFrom("ourbooks.pellegrinelli@gmail.com");
	        message.setTo(u.getEmail()); 
	        message.setSubject("Consegna libro OurBooks");
	        if(i == 0)
	        	text = "Ciao! Hanno acquistato il tuo libro \"" + specifiche.getLibro().getTitolo() + "\"! devi consegnarlo a ";
	        else
	        	text = "Ciao! Sei stato scelto per consegnare il libro \"" + specifiche.getLibro().getTitolo() + "\" a ";
	        text += u_ricevitore.getEmail() + ".\n";
	        text += "Riceverai " + specifiche.getTokens().get(i) + " token.\n";
	        text += "L'utente si trova alle seguenti coordinate:\n";
	        text += "latitudine: " + u_ricevitore.getLat() + "\n";
	        text += "longitudine: " + u_ricevitore.getLon() + "\n";
	        text += "Contatta direttamente l'utente per accordarti su data e orario della consegna.";
	        message.setText(text);
	        emailSender.send(message);
	        
	        u_ricevitore = u;
		}
		return compratore;
	}
	
}