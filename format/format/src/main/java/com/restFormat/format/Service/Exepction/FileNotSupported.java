package com.restFormat.format.Service.Exepction;

public class FileNotSupported extends Exception {
    public FileNotSupported(){
        super("Application haven't support that format.");
    }
}
