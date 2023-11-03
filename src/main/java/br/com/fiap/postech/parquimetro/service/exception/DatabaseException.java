package br.com.fiap.postech.parquimetro.service.exception;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg) {
        super(msg);
    }
}

