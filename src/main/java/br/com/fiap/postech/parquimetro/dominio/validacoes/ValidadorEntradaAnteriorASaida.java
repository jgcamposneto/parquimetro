package br.com.fiap.postech.parquimetro.dominio.validacoes;

import br.com.fiap.postech.parquimetro.dto.DadosRegistroEstacionamentoDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidadorEntradaAnteriorASaida implements  ValidadorRegistroDeEstacionamento {

    @Override
    public void validar(DadosRegistroEstacionamentoDTO dados) {
    }
}
