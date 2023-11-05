package br.com.fiap.postech.parquimetro.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCondutorDTO(
        @NotBlank(message = "Nome deve ser informado.")
        String nome,
        @NotNull(message = "Endereço deve ser informado.")
        @Valid
        DadosEnderecoDTO endereco,
        @NotBlank(message = "Contat deve ser informado.")
        String contato
) {
}
