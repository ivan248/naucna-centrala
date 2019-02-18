package com.udd.naucnacentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udd.naucnacentrala.domain.Magazine;
import com.udd.naucnacentrala.domain.PaymentType;
import com.udd.naucnacentrala.repository.MagazineRepository;

@Component
public class CheckMagazineType implements JavaDelegate {

    @Autowired
    private MagazineRepository magazineRepository;
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Starting task CheckMagazineType...");
        System.out.println("Magazin with ID:" + execution.getVariable("magazineId"));

        final Long magazineId = (Long)execution.getVariable("magazineId");
        final Magazine magazine = magazineRepository.findById(magazineId).get();

        if(magazine.getPaymentType().equals(PaymentType.OPENACCESS)){
            execution.setVariable("isOpenAccess", true);
        } else {
            execution.setVariable("isOpenAccess", false);
        }

        System.out.println("Ending task CheckMagazineType...");
    }
}
