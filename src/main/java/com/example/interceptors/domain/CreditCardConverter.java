package com.example.interceptors.domain;


import com.example.interceptors.config.SpringContextHelper;
import com.example.interceptors.services.EncryptionService;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CreditCardConverter implements AttributeConverter<String,String> {
    @Override
    public String convertToDatabaseColumn(String s) {
        return getEncryptionService ().encrypt (s);
    }
    
    @Override
    public String convertToEntityAttribute(String s) {
        return getEncryptionService ().decrypt (s);
    }
    
    private EncryptionService getEncryptionService(){
        
        return SpringContextHelper.getApplicationContext ().getBean (EncryptionService.class);
    }
}