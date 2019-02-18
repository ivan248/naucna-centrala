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
public class SendSuccessEmail implements JavaDelegate {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
 	
    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	
    	Long authorId = Long.parseLong((String)execution.getVariable("authorId"));
    	System.out.println("SendSuccessEmail sending success email to author with ID: " + authorId);
    	User author = userService.findById(authorId);
    	
    	SimpleMailMessage authorEmail = new SimpleMailMessage();
		authorEmail.setTo(author.getEmail());
		authorEmail.setSubject("Succesfully submited paper notification email");
		authorEmail.setText("Dear Sir/Madam, Congratulations, your scientific paper has been published sucesfully!");
		authorEmail.setFrom("noreply@domain.com");

		emailService.sendEmail(authorEmail);
	}
}
