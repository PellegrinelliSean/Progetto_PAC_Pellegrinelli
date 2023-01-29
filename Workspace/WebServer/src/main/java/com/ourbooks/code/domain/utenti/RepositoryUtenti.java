package com.ourbooks.code.domain.utenti;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepositoryUtenti extends MongoRepository<Utente, String>{
	
    @Query("{id:'?0'}")
    Utente findItemById(String id);
    
    @Query("{email:'?0'}")
    Utente findItemByEmail(String email);

    @Query("{}")
    List<Utente> findAll();
}
