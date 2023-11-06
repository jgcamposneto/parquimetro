package br.com.fiap.postech.parquimetro.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CalculadoraDeValorDeEstacionamento {

    @Autowired
    private List<ICalculoDeEstacionamento> calculos;

    // Retorna o cálculo que tiver maior valor
    public BigDecimal calcular(Estacionamento estacionamento) {
        return calculos.stream().map(c -> c.calcular(estacionamento)).reduce(BigDecimal.ZERO, BigDecimal::max);
    }

}
