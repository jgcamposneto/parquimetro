package br.com.fiap.postech.parquimetro.controller.exception;

import br.com.fiap.postech.parquimetro.service.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private final DefaultError error = new DefaultError();

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<DefaultError> entityNotFound(ControllerNotFoundException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        DadosErroValidacao dadosErroValidacao = new DadosErroValidacao("", "Entidade não encontrada.");
        ArrayList<DadosErroValidacao> dadosErroValidacoes = new ArrayList<>();
        dadosErroValidacoes.add(dadosErroValidacao);
        error.setErrors(dadosErroValidacoes);
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultError> entityNotFound(MethodArgumentNotValidException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        var fieldErrors = exception.getFieldErrors();
        error.setErrors(fieldErrors.stream().map(DadosErroValidacao::new).toList());
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DefaultError> entityNotFound(DataIntegrityViolationException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        DadosErroValidacao dadosErroValidacao = new DadosErroValidacao("", "Violação de integridade do banco de dados.");
        ArrayList<DadosErroValidacao> dadosErroValidacoes = new ArrayList<>();
        dadosErroValidacoes.add(dadosErroValidacao);
        error.setErrors(dadosErroValidacoes);
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.error);
    }


    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<DefaultError> entityNotFound(DatabaseException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        DadosErroValidacao dadosErroValidacao = new DadosErroValidacao("", "Database error.");
        ArrayList<DadosErroValidacao> dadosErroValidacoes = new ArrayList<>();
        dadosErroValidacoes.add(dadosErroValidacao);
        error.setErrors(dadosErroValidacoes);
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.error);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<DefaultError> entity(ServiceException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        DadosErroValidacao dadosErroValidacao = new DadosErroValidacao("", "Entidade já cadastrada.");
        ArrayList<DadosErroValidacao> dadosErroValidacoes = new ArrayList<>();
        dadosErroValidacoes.add(dadosErroValidacao);
        error.setErrors(dadosErroValidacoes);
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.error);
    }

}
