package com.udd.naucnacentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udd.naucnacentrala.config.EmailUtils;
import com.udd.naucnacentrala.domain.User;
import com.udd.naucnacentrala.service.UserService;

@Component
public class SendEmailBadFormating implements JavaDelegate {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailUtils emailService;
 	
    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	
    	Long authorId = Long.parseLong((String)execution.getVariable("authorId"));
    	System.out.println("SendFailureEmail sending failure email to author with ID: " + authorId);
    	User author = userService.findById(authorId);
    	
    	
//    	emailService.sendEmail(author.getEmail(),
//				"New article submitted",
//				"Your article has been submitted. Please wait for a review.");
	}
}
