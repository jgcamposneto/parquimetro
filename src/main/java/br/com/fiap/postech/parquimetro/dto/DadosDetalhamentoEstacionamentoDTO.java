package br.com.fiap.postech.parquimetro.dto;

import br.com.fiap.postech.parquimetro.dominio.Duracao;
import br.com.fiap.postech.parquimetro.dominio.Estacionamento;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosDetalhamentoEstacionamentoDTO(
        UUID id,
        LocalDateTime entrada,
        int duracao,
        UUID idVeiculo
        ) {

    public DadosDetalhamentoEstacionamentoDTO(Estacionamento estacionamento) {
        this(estacionamento.getId(),
                estacionamento.getEntrada(),
                estacionamento.getDuracao(),
                estacionamento.getVeiculo().getId());
    }

}
