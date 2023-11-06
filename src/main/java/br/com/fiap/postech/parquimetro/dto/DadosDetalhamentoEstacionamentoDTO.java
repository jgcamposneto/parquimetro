package br.com.fiap.postech.parquimetro.dto;

import br.com.fiap.postech.parquimetro.domain.Estacionamento;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosDetalhamentoEstacionamentoDTO(
        UUID id,
        LocalDateTime entrada,
        int duracaoContratadaEmHoras,
        UUID idVeiculo,
        boolean pago,
        boolean ativo
) {

    public DadosDetalhamentoEstacionamentoDTO(Estacionamento estacionamento) {
        this(estacionamento.getId(),
                estacionamento.getEntrada(),
                estacionamento.getDuracaoContratadaEmHoras(),
                estacionamento.getVeiculo().getId(),
                estacionamento.isPago(),
                estacionamento.isAtivo());
    }

}
