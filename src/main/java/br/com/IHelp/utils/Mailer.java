package br.com.IHelp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class Mailer {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	SpringTemplateEngine template;
	
	@Bean
	public void enviarEmailRedefinição(String email) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    	Context context = new Context();
    	context.setVariable("titulo", "Redefinição de senha");
    	context.setVariable("texto", "Deseja redefinir sua senha? Clique no botão a baixo");
    	
    	String html = template.process("email/email", context);
    	
		simpleMailMessage.setFrom("helpcar@gmail.com");
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Redefinição de senha");
		simpleMailMessage.setText(html);
		
		javaMailSender.send(simpleMailMessage);
	}
}
