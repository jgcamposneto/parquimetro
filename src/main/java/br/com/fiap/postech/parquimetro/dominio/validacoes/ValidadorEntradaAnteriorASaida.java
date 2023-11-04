package br.com.fiap.postech.parquimetro.dominio.validacoes;

import br.com.fiap.postech.parquimetro.dominio.ValidacaoException;
import br.com.fiap.postech.parquimetro.dto.DadosRegistroEstacionamentoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorEntradaAnteriorASaida implements  ValidadorRegistroDeEstacionamento {

    @Override
    public void validar(DadosRegistroEstacionamentoDTO dados) {
        var entrada = dados.entrada();
        var saida = dados.saida();
        if(entrada.isAfter(saida) || entrada.isEqual(saida)) {
            throw new ValidacaoException("Data de entrada é maior ou igual que a data de saída");
        }

    }
}
