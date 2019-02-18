package com.udd.naucnacentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.udd.naucnacentrala.service.UserService;

public class IndexingWithDOI implements JavaDelegate {
    
	@Autowired
	private UserService userService;
	
 	
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Starting task IndexingWithDOI...");
        
        System.out.println("Ending task IndexingWithDOI...");	

	}
}
