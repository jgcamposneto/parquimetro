package br.com.fiap.postech.parquimetro.dto;

import br.com.fiap.postech.parquimetro.domain.Estacionamento;
import br.com.fiap.postech.parquimetro.domain.TempoDecorrido;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosMonitoramentoEstacionamentoDTO(
        UUID id,
        LocalDateTime entrada,
        int duracaoContratadaEmHoras,
        TempoDecorrido tempoDecorrido,
        UUID idVeiculo
) {

    public DadosMonitoramentoEstacionamentoDTO(Estacionamento estacionamento) {
        this(estacionamento.getId(),
                estacionamento.getEntrada(),
                estacionamento.getDuracaoContratadaEmHoras(),
                estacionamento.calcularTempoDecorrido(),
                estacionamento.getVeiculo().getId());
    }

}