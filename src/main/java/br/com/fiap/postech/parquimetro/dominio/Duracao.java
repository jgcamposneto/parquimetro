package br.com.fiap.postech.parquimetro.dominio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Duracao {

    private long horas;
    private long minutos;
    private long segundos;

    public Duracao setHoras(long horas) {
        this.horas = horas;
        return this;
    }

    public Duracao setMinutos(long minutos) {
        this.minutos = minutos;
        return this;
    }

    public Duracao setSegundos(long segundos) {
        this.segundos = segundos;
        return this;
    }
}
