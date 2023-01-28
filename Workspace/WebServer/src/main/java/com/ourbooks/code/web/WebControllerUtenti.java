package com.ourbooks.code.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ourbooks.code.domain.utenti.ServizioUtenti;

@RestController
public class WebControllerUtenti {
	
	@Autowired 
	private ServizioUtenti servizio;

}
