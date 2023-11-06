package br.com.fiap.postech.parquimetro.dto;

import br.com.fiap.postech.parquimetro.domain.Veiculo;

import java.util.UUID;

public record DadosDetalhamentoVeiculoDTO(
        UUID id,
        String placa,
        UUID idCondutor
) {

    public DadosDetalhamentoVeiculoDTO(Veiculo veiculo) {
        this(veiculo.getId(), veiculo.getPlaca(), veiculo.getCondutor().getId());
    }

}
