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
            LocalDateTime entrada = estacionamento.getEntrada();
            LocalDateTime agora = LocalDateTime.now();
            long diferencaDeHoras = ChronoUnit.HOURS.between(entrada, agora);
            return new BigDecimal(diferencaDeHoras);
        }
        return BigDecimal.ZERO;

    }
}
