package br.com.fiap.postech.parquimetro.dominio;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EstacionamentoTest {

    @Test
    void calcularTempoDeEstacionamento() {

        // ARRANGE
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = entrada.plusHours(1).plusMinutes(20).plusMinutes(41).plusSeconds(1);

        Estacionamento estacionamento =
                new Estacionamento()
                        .setEntrada(entrada);
//                        .setSaida(saida);

        // ACT
        //Duracao duracao = estacionamento.calcularTempoDeEstacionamento();

        // ASSERT
//        assertEquals(duracao.getHoras(),2);
//        assertEquals(duracao.getMinutos(),1);
//        assertEquals(duracao.getSegundos(),1);

    }
}