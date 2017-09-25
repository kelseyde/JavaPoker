package com.example.kelseyde.poker.models;


public class ConsoleLogger implements Logger {
    public void log(String message) {
        System.out.println(message);
    }
}
