package br.com.fiap.postech.parquimetro.dominio.validacoes;

import br.com.fiap.postech.parquimetro.dto.DadosRegistroEstacionamentoDTO;

public interface ValidadorRegistroDeEstacionamento {

    void validar(DadosRegistroEstacionamentoDTO dados);

}
