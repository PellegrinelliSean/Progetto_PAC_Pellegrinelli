package com.ourbooks.code.domain.utenti;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


/**
 * L'interfaccia RepositoryUtenti. L'iterfaccia per la comunicazione con il database MongoDB.
 */
public interface RepositoryUtenti extends MongoRepository<Utente, String>{
	
    /**
     * Ricerca dell'utente in base all'id.
     *
     * @param id l'id
     * @return l'utente
     */
    @Query("{id:'?0'}")
    Utente findItemById(String id);
    
    /**
     * Ricerca dell'utente in base all'email.
     *
     * @param email la email dell'utente da cercare
     * @return l'utente
     */
    @Query("{email:'?0'}")
    Utente findItemByEmail(String email);

    /**
     * Ricerca di tutti gli utenti
     *
     * @return la lista di tutti gli utenti nel DB
     */
    @Query("{}")
    List<Utente> findAll();
    
    
}
