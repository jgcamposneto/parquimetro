package br.com.fiap.postech.parquimetro.domain.validation;

import br.com.fiap.postech.parquimetro.domain.Estacionamento;
import br.com.fiap.postech.parquimetro.domain.FormaDePagamento;
import br.com.fiap.postech.parquimetro.domain.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorFormaDePagamentoCompativelComPeriodo implements IValidadorPagamentoDeEstacionamento {

    @Override
    public void validar(Estacionamento estacionamento) {

        FormaDePagamento formaDePagamento = estacionamento.getVeiculo().getCondutor().getFormaDePagamento();
        boolean pagamentoPix = formaDePagamento.equals(FormaDePagamento.PIX);
        if(!estacionamento.isContratacaoTempoFixo() && pagamentoPix) {
            throw new ValidacaoException("A forma de pagamento via Pix só está disponível para período fixos.");
        }

    }
}
