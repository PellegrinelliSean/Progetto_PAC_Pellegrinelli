package com.ourbooks.code;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class testEmail {
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Test
	public void testListaAcquistabili() {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("ourbooks.pellegrinelli@gmail.com");
        message.setTo("sean.pellegrinellicheinrealtànonesiste@gmail.com"); 
        message.setSubject("Email Spring Boot 2"); 
        message.setText("email di prova 2");
        emailSender.send(message);
	}
}
