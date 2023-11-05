package br.com.fiap.postech.parquimetro.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroVeiculoDTO(

        @NotBlank(message = "Placa do veículo deve ser informada.")
        String placa
) {
}
