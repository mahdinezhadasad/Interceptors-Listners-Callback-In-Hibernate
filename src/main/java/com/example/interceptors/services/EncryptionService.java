package com.example.interceptors.services;

public interface EncryptionService {
    
    String encrypt(String freeText);
    
    String decrypt(String encryptedText);
}