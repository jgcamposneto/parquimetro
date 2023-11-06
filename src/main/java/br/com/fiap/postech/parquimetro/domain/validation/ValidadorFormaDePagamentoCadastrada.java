package br.com.fiap.postech.parquimetro.domain.validation;

import br.com.fiap.postech.parquimetro.domain.Condutor;
import br.com.fiap.postech.parquimetro.domain.Estacionamento;
import br.com.fiap.postech.parquimetro.domain.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorFormaDePagamentoCadastrada implements IValidadorPagamentoDeEstacionamento {
    @Override
    public void validar(Estacionamento estacionamento) {
        Condutor condutor = estacionamento.getVeiculo().getCondutor();
        if (condutor.getFormaDePagamento() == null) {
            throw new ValidacaoException("Forma de pagamento deve ser cadastrada para realizar o pagamento!");
        }
    }
}
