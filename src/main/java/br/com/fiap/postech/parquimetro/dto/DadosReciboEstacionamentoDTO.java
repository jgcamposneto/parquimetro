package br.com.fiap.postech.parquimetro.dto;

import br.com.fiap.postech.parquimetro.domain.Estacionamento;
import br.com.fiap.postech.parquimetro.domain.ICalculoDeEstacionamento;
import br.com.fiap.postech.parquimetro.domain.TempoDecorrido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosReciboEstacionamentoDTO(
        LocalDateTime entrada,
        LocalDateTime saida,
        TempoDecorrido tempoEstacionado,
        BigDecimal tarifaAplicada,
        BigDecimal valorPago

                                           ) {

        public DadosReciboEstacionamentoDTO(Estacionamento estacionamento) {
                this(
                        estacionamento.getEntrada(),
                        estacionamento.getSaida(),
                        estacionamento.calcularTempoDecorrido(estacionamento.getSaida()),
                        ICalculoDeEstacionamento.TARIFA,
                        estacionamento.getValor()
                );
        }
}
