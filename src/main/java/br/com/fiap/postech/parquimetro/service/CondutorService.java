package br.com.fiap.postech.parquimetro.service;

import br.com.fiap.postech.parquimetro.dominio.Condutor;
import br.com.fiap.postech.parquimetro.dominio.Veiculo;
import br.com.fiap.postech.parquimetro.dto.DadosAtualizacaoFormaPagamentoDTO;
import br.com.fiap.postech.parquimetro.dto.DadosCadastroVeiculoDTO;
import br.com.fiap.postech.parquimetro.dto.DadosDetalhamentoCondutorDTO;
import br.com.fiap.postech.parquimetro.dto.DadosDetalhamentoVeiculoDTO;
import br.com.fiap.postech.parquimetro.repository.ICondutorRepository;
import br.com.fiap.postech.parquimetro.service.exception.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CondutorService {

    @Autowired
    private ICondutorRepository repository;

    @Autowired
    private VeiculoService veiculoService;

    public Condutor findById(UUID id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Condutor não encontrado"));
    }

    public Condutor save(Condutor condutor) {
        return repository.save(condutor);
    }

    public DadosDetalhamentoVeiculoDTO cadastrarVeiculo(UUID idCondutor, DadosCadastroVeiculoDTO dados) {
        var condutor = findById(idCondutor);
        var veiculo = new Veiculo().setPlaca(dados.placa()).setCondutor(condutor);
        condutor.addVeiculo(veiculo);
        veiculoService.save(veiculo);
        return new DadosDetalhamentoVeiculoDTO(veiculo);
    }

    @Transactional
    public DadosDetalhamentoCondutorDTO atualizarFormaPagamento(UUID id, DadosAtualizacaoFormaPagamentoDTO dados) {
        var condutor = findById(id);
        condutor.setFormaDePagamento(dados.formaDePagamento());
        return new DadosDetalhamentoCondutorDTO(condutor);
    }
}
