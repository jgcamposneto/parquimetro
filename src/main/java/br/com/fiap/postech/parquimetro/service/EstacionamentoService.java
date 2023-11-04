package br.com.fiap.postech.parquimetro.service;

import br.com.fiap.postech.parquimetro.dominio.Estacionamento;
import br.com.fiap.postech.parquimetro.dominio.ValidacaoException;
import br.com.fiap.postech.parquimetro.dominio.Veiculo;
import br.com.fiap.postech.parquimetro.dominio.validacoes.ValidadorRegistroDeEstacionamento;
import br.com.fiap.postech.parquimetro.dto.DadosDetalhamentoEstacionamentoDTO;
import br.com.fiap.postech.parquimetro.dto.DadosRegistroEstacionamentoDTO;
import br.com.fiap.postech.parquimetro.repository.IEstacionamentoRepository;
import br.com.fiap.postech.parquimetro.service.exception.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EstacionamentoService {

    @Autowired
    private IEstacionamentoRepository repository;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private List<ValidadorRegistroDeEstacionamento> validadores;

    public Page<Estacionamento> findAll(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    @Cacheable("estacionamentos")
    public Estacionamento findById(UUID id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Estacionamento não encontrado"));
    }

    public DadosDetalhamentoEstacionamentoDTO registrar(DadosRegistroEstacionamentoDTO dados) {

        if(!veiculoService.existsById(dados.idVeiculo())) {
            throw new ValidacaoException("Id do veículo informado não existe.");
        }

        validadores.forEach(v -> v.validar(dados));

        var veiculo = veiculoService.findById(dados.idVeiculo());

        var estacionamento =
                new Estacionamento()
                        .setEntrada(dados.entrada())
                        .setSaida(dados.saida())
                        .setVeiculo(veiculo)
                        .setDocumentoDoCondutor(dados.documentoDoCondutor());

        repository.save(estacionamento);

        return new DadosDetalhamentoEstacionamentoDTO(estacionamento);

    }
}
