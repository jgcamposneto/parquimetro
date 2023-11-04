package br.com.fiap.postech.parquimetro.dto;

import br.com.fiap.postech.parquimetro.dominio.Estacionamento;
import br.com.fiap.postech.parquimetro.dominio.Veiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosRegistroEstacionamentoDTO(
        @NotNull(message = "Data de entrada do veículo no estacionamento deve ser informada.")
        @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
        LocalDateTime entrada,
        @NotNull(message = "Data de saída do veículo no estacionamento deve ser informada.")
        @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
        LocalDateTime saida,
        @NotBlank(message = "Documento do condutor deve ser informado.")
        String documentoDoCondutor,
        @NotNull(message = "Id do veículo deve ser informado.")
        UUID idVeiculo
) {

}
