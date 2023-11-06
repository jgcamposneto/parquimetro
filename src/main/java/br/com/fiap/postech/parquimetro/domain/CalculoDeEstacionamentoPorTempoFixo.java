package br.com.fiap.postech.parquimetro.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculoDeEstacionamentoPorTempoFixo implements ICalculoDeEstacionamento {

    // Para tempo fixo, o valor total cobrado é independente do tempo real utilizado.
    @Override
    public BigDecimal calcular(Estacionamento estacionamento) {
        return estacionamento.isContratacaoTempoFixo() ?
             ICalculoDeEstacionamento.TARIFA.multiply(new BigDecimal(estacionamento.getDuracaoContratadaEmHoras()))
                :
                BigDecimal.ZERO;
    }
}
