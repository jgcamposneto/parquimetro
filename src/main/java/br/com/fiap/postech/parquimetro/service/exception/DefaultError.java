package br.com.fiap.postech.parquimetro.service.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.List;

@Getter
public class DefaultError {
    private Instant timestamp;
    private Integer status;
    private List<DadosErroValidacao> errors;
    private String message;
    private String path;

    public DefaultError() {}

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setErrors(List<DadosErroValidacao> errors) {
        this.errors = errors;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
