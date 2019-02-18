package com.udd.naucnacentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.udd.naucnacentrala.domain.User;
import com.udd.naucnacentrala.service.UserService;
import com.udd.naucnacentrala.service.impl.EmailService;

@Component
public class SendFailureEmail implements JavaDelegate {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
 	
    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	
    	Long authorId = Long.parseLong((String)execution.getVariable("authorId"));
    	System.out.println("SendFailureEmail sending failure email to author with ID: " + authorId);
    	User author = userService.findById(authorId);
    	
    	
    	SimpleMailMessage authorEmail = new SimpleMailMessage();
		authorEmail.setTo(author.getEmail());
		authorEmail.setSubject("New article failure in submission notification email");
		authorEmail.setText("Greetings, We are sorry, but your scientific paper has been rejected.");
		authorEmail.setFrom("noreply@domain.com");

		emailService.sendEmail(authorEmail);
	}
}
