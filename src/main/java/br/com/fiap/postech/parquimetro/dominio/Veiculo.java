package br.com.fiap.postech.parquimetro.dominio;

import br.com.fiap.postech.parquimetro.dto.DadosAtualizacaoVeiculoDTO;
import br.com.fiap.postech.parquimetro.dto.DadosCadastroVeiculoDTO;
import br.com.fiap.postech.parquimetro.dto.DadosDetalhamentoVeiculoDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true,nullable = false)
    private String placa;

    @OneToMany(mappedBy = "veiculo", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Estacionamento> estacionamentos = new ArrayList<>();

    public Veiculo(DadosCadastroVeiculoDTO dto) {
        this.placa = dto.placa();
    }

    public Veiculo setId(UUID id) {
        this.id = id;
        return this;
    }

    public Veiculo setPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public void addEstacionamento(Estacionamento estacionamento) {
        this.getEstacionamentos().add(estacionamento);
        estacionamento.setVeiculo(this);
    }

    public void atualizarInformacoes(DadosAtualizacaoVeiculoDTO dados) {
        if(dados.placa() != null) {
            this.placa = dados.placa();
        }
    }
}
