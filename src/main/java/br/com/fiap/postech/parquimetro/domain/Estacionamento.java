package br.com.fiap.postech.parquimetro.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
@Table(name = "tb_estacionamento")
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "veiculo.id")
    private Veiculo veiculo;

    private LocalDateTime entrada;

    private int duracaoContratadaEmHoras;

    private boolean ativo;

    private BigDecimal valor;

    private boolean pago;

    public Estacionamento setId(UUID id) {
        this.id = id;
        return this;
    }

    public Estacionamento setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        return this;
    }

    public Estacionamento setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
        return this;
    }

    public Estacionamento setDuracaoContratadaEmHoras(int duracao) {
        this.duracaoContratadaEmHoras = duracao;
        return this;
    }

    public Estacionamento setAtivo(boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public Estacionamento setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Estacionamento setPago(boolean pago) {
        this.pago = pago;
        return this;
    }

    public boolean isContratacaoTempoFixo() {
        return duracaoContratadaEmHoras > 0;
    }

    public TempoDecorrido calcularTempoDecorrido() {
        LocalDateTime dataTemporaria = LocalDateTime.from(entrada);
        LocalDateTime agora = LocalDateTime.now();
        long horas = dataTemporaria.until(agora, ChronoUnit.HOURS);
        long minutosTotais = dataTemporaria.until(agora, ChronoUnit.MINUTES);
        long minutos = horas == 0 ? minutosTotais : minutosTotais % 60;
        return new TempoDecorrido(horas, minutos);
    }
}
