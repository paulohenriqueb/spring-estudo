package com.minhaempresa.spring.application.services.exceptions;

public class DatabaseException extends RuntimeException{
    private static final long seriarVersionUID = 1L;

    public DatabaseException(String msg){
        super(msg);
    }
}
