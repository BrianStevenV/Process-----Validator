package com.restFormat.format.Service.Exepction;

public class WrongConstructorException extends Exception {
    public WrongConstructorException(){
        super("You should joind correct File");
    }
}
