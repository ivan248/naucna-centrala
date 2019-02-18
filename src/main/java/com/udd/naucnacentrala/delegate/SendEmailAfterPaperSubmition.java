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
public class SendEmailAfterPaperSubmition implements JavaDelegate {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
 	
    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	
    	Long authorId = Long.parseLong((String)execution.getVariable("authorId"));
    	System.out.println("SendEmailAfterPaperSubmition sending email to author with ID: " + authorId);
    	User author = userService.findById(authorId);

    	Long mainEditorId = Long.parseLong((String)execution.getVariable("mainEditorId"));
    	System.out.println("SendEmailAfterPaperSubmition sending email to main editor with ID: " + mainEditorId);
    	User mainEditor = userService.findById(mainEditorId);
    	
		SimpleMailMessage authorEmail = new SimpleMailMessage();
		authorEmail.setTo(author.getEmail());
		authorEmail.setSubject("New article submission notification email");
		authorEmail.setText("Congratalations! You succesfully submitted an article. Please wait for a review.");
		authorEmail.setFrom("noreply@domain.com");

		emailService.sendEmail(authorEmail);
		
		SimpleMailMessage MainEditorEmail = new SimpleMailMessage();
		MainEditorEmail.setTo(mainEditor.getEmail());
		MainEditorEmail.setSubject("\"New article submission notification email");
		MainEditorEmail.setText("There is a new article in the Naucna centrala. Please login to see it.");
		MainEditorEmail.setFrom("noreply@domain.com");

		emailService.sendEmail(MainEditorEmail);
    	
	}
    
}
