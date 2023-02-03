package com.example.interceptors.domain;

import com.example.interceptors.config.SpringContextHelper;
import com.example.interceptors.services.EncryptionService;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CreditCardJPACallback {
    
    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard){
    
        System.out.println ("before update was called ..");
        
        creditCard.setCreditCardNumber (getEncryptionService ().encrypt (creditCard.getCreditCardNumber ()));
    }
    
    
    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard){
    
    
        System.out.println ("Post Load was called ...");
        creditCard.setCreditCardNumber (getEncryptionService ().decrypt (creditCard.getCreditCardNumber ()));
    }
    
    private EncryptionService getEncryptionService(){
        
        return SpringContextHelper.getApplicationContext ().getBean (EncryptionService.class);
    }
}