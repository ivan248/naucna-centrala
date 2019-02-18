package com.udd.naucnacentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
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
    	
    	
//    	emailService.sendEmail(author.getEmail(),
//				"New article submitted",
//				"Your article has been submitted. Please wait for a review.");
//    	emailService.sendEmail(mainEditor.getEmail(),
//				"New article submitted",
//				"New article has been submitted, login to Scientific Center to view it.");
	}
    
}
