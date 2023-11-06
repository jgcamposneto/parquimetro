package br.com.fiap.postech.parquimetro.domain.validation;

import br.com.fiap.postech.parquimetro.domain.Estacionamento;

public interface IValidadorPagamentoDeEstacionamento {

    void validar(Estacionamento estacionamento);
}
