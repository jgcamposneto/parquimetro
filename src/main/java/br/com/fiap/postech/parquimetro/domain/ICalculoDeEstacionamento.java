package br.com.fiap.postech.parquimetro.domain;

import java.math.BigDecimal;

public interface ICalculoDeEstacionamento {

    public final BigDecimal TARIFA = new BigDecimal("10.00");
    public BigDecimal calcular(Estacionamento estacionamento);

}
