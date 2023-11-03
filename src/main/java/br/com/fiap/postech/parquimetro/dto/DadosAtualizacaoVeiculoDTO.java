package br.com.fiap.postech.parquimetro.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosAtualizacaoVeiculoDTO(
        @NotNull
        UUID id,
        String placa) {
}
