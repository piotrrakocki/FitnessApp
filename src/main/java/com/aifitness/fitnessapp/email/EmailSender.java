package com.aifitness.fitnessapp.email;

public interface EmailSender {

    void send(String to, String email, String subject);
}
