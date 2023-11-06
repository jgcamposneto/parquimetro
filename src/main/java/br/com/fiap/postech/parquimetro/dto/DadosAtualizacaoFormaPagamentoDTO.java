package br.com.fiap.postech.parquimetro.dto;

import br.com.fiap.postech.parquimetro.domain.FormaDePagamento;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoFormaPagamentoDTO(
        @NotNull(message = "Forma de pagamento deve ser informada.")
        FormaDePagamento formaDePagamento
) {
}
