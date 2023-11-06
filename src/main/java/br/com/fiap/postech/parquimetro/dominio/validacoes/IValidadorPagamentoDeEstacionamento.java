package br.com.fiap.postech.parquimetro.dominio.validacoes;

import br.com.fiap.postech.parquimetro.dominio.Estacionamento;

public interface IValidadorPagamentoDeEstacionamento {

    void validar(Estacionamento estacionamento);
}
