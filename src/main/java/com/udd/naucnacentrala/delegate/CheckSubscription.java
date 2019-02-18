package com.udd.naucnacentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;


@Component
public class CheckSubscription implements JavaDelegate {
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Starting task CheckSubscription...");
        System.out.println("Calling the payment concentrator service...");
        execution.setVariable("subscriptionPayed", true);
        System.out.println("Payment succesfull...");
        System.out.println("Ending task CheckSubscription...");
    }
}
