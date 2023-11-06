package br.com.fiap.postech.parquimetro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosRegistroEstacionamentoDTO(
        @NotNull(message = "Data de entrada do veículo no estacionamento deve ser informada.")
        @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
        LocalDateTime entrada,
        @Min(value = 0, message = "A duração deve ser um valor positivo")
        int duracaoEmHoras,
        @NotNull(message = "Id do veículo deve ser informado.")
        UUID idVeiculo
) {

}
