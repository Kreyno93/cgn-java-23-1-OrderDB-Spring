package de.neuefische.cgnjava231orderdbspring.service;

import org.springframework.stereotype.Service;

@Service
public class GenerateUUID {

    public String generateUUID() {
        return java.util.UUID.randomUUID().toString();
    }

}
