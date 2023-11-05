package br.com.fiap.postech.parquimetro.dominio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
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

    private Integer duracao;

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

    public Estacionamento setDuracao(Integer duracao) {
        this.duracao = duracao;
        return this;
    }
}
