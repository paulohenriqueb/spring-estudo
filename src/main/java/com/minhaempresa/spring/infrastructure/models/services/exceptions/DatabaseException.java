package com.minhaempresa.spring.infrastructure.models.services.exceptions;

public class DatabaseException extends RuntimeException{
    private static final long seriarVersionUID = 1L;

    public DatabaseException(String msg){
        super(msg);
    }
}
