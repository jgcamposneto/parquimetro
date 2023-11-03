package br.com.fiap.postech.parquimetro.service.exception;

public class ControllerNotFoundException extends RuntimeException {
    public ControllerNotFoundException(String message){
        super(message);
    }
}
