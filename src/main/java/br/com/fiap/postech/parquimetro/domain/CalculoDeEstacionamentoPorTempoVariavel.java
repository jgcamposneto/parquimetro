package br.com.fiap.postech.parquimetro.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class CalculoDeEstacionamentoPorTempoVariavel implements ICalculoDeEstacionamento {

    // Se a escolha foi por período variável, calcula a diferença de horas completas
    @Override
    public BigDecimal calcular(Estacionamento estacionamento) {
        if (!estacionamento.isContratacaoTempoFixo()) {
            var entrada = estacionamento.getEntrada();
            var saida = estacionamento.getSaida();
            long diferencaDeHoras = ChronoUnit.HOURS.between(entrada, saida);
            return ICalculoDeEstacionamento.TARIFA.multiply(new BigDecimal(diferencaDeHoras));
        }
        return BigDecimal.ZERO;
    }
}
