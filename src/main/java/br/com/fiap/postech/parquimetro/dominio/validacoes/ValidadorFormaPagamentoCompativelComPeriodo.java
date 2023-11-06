package br.com.fiap.postech.parquimetro.dominio.validacoes;

import br.com.fiap.postech.parquimetro.dominio.Estacionamento;
import br.com.fiap.postech.parquimetro.dominio.FormaDePagamento;
import br.com.fiap.postech.parquimetro.dominio.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorFormaPagamentoCompativelComPeriodo implements IValidadorPagamentoDeEstacionamento {

    @Override
    public void validar(Estacionamento estacionamento) {

        FormaDePagamento formaDePagamento = estacionamento.getVeiculo().getCondutor().getFormaDePagamento();
        boolean pagamentoPix = formaDePagamento.equals(FormaDePagamento.PIX);
        if(!estacionamento.isContratacaoTempoFixo() && pagamentoPix) {
            throw new ValidacaoException("A forma de pagamento via Pix só está disponível para período fixos.");
        }

    }
}
