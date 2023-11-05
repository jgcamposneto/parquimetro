package br.com.fiap.postech.parquimetro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEnderecoDTO(
        @NotBlank(message = "Endereço não pode ser vazio.")
        String logradouro,
        @NotBlank(message = "Bairro não pode ser vazio.")
        String bairro,
        @NotBlank(message = "CEP não pode ser vazio.")
        @Pattern(regexp = "\\d{8}", message = "Formato inválido para o CEP.")
        String cep,
        @NotBlank(message = "Cidade não pode ser vazio.")
        String cidade,
        @NotBlank(message = "UF não pode ser vazio.")
        String uf,
        String complemento,
        String numero
) {
}
