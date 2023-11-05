package br.com.fiap.postech.parquimetro.dominio;

import br.com.fiap.postech.parquimetro.dto.DadosCadastroCondutorDTO;
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
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_condutor")
public class Condutor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;


    @Embedded
    private Endereco endereco;

    private String contato;

    @OneToMany(mappedBy = "condutor", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Veiculo> veiculos = new ArrayList<>();

    public Condutor(DadosCadastroCondutorDTO dados) {
        this.nome = dados.nome();
        this.endereco = new Endereco(dados.endereco());
        this.contato = dados.contato();
    }

    public void addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

}
