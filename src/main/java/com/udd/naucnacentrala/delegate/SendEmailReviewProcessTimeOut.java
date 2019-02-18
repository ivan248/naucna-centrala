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
public class SendEmailReviewProcessTimeOut implements JavaDelegate {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
 	
    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	
    	Long mainEditorId = Long.parseLong((String)execution.getVariable("mainEditorId"));
    	System.out.println("SendEmailAfterPaperSubmition sending email to main editor with ID: " + mainEditorId);
    	User mainEditor = userService.findById(mainEditorId);
		SimpleMailMessage MainEditorEmail = new SimpleMailMessage();
		
		MainEditorEmail.setTo(mainEditor.getEmail());
		MainEditorEmail.setSubject("\"Review process timed out notification email");
		MainEditorEmail.setText("Dear Sir/Madam, The time has run out for reviewing the scientific paper.");
		MainEditorEmail.setFrom("noreply@domain.com");

		emailService.sendEmail(MainEditorEmail);
    	
	}
}
