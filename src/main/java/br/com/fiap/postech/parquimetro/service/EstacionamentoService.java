package br.com.fiap.postech.parquimetro.service;

import br.com.fiap.postech.parquimetro.dominio.CalculadoraDeValorDeEstacionamento;
import br.com.fiap.postech.parquimetro.dominio.Estacionamento;
import br.com.fiap.postech.parquimetro.dominio.ValidacaoException;
import br.com.fiap.postech.parquimetro.dominio.validacoes.IValidadorPagamentoDeEstacionamento;
import br.com.fiap.postech.parquimetro.dto.*;
import br.com.fiap.postech.parquimetro.repository.IEstacionamentoRepository;
import br.com.fiap.postech.parquimetro.service.exception.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class EstacionamentoService {

    @Autowired
    private IEstacionamentoRepository repository;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private List<IValidadorPagamentoDeEstacionamento> validaroresPagamento;

    public Page<Estacionamento> findAll(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public List<Estacionamento> consultarAtivos() {
        return repository.findByAtivoTrue();
    }

    public DadosMonitoramentoEstacionamentoDTO findById(UUID id) {
        return new DadosMonitoramentoEstacionamentoDTO(buscarEstacionamento(id));
    }

    public DadosDetalhamentoEstacionamentoDTO registrar(DadosRegistroEstacionamentoDTO dados) {

        if(!veiculoService.existsById(dados.idVeiculo())) {
            throw new ValidacaoException("Veículo informado não está cadastrado.");
        }
        var veiculo = veiculoService.findById(dados.idVeiculo());
        var estacionamento =
                new Estacionamento()
                        .setEntrada(dados.entrada())
                        .setVeiculo(veiculo)
                        .setDuracaoContratadaEmHoras(dados.duracao())
                        .setAtivo(true)
                        .setPago(false);
        repository.save(estacionamento);
        return new DadosDetalhamentoEstacionamentoDTO(estacionamento);
    }

    @Transactional
    public DadosDetalhamentoEstacionamentoDTO pagar(UUID id) {
        var estacionamentoValido = buscarEstacionamento(id);
        validaroresPagamento.forEach(v -> v.validar(estacionamentoValido));
        estacionamentoValido.setPago(true);
        return new DadosDetalhamentoEstacionamentoDTO(estacionamentoValido);
    }

    private Estacionamento buscarEstacionamento(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Estacionamento não encontrado"));
    }

    @Transactional
    public DadosEncerramentoEstacionamentoDTO encerrar(UUID id) {
        Estacionamento estacionamento = buscarEstacionamento(id);
        CalculadoraDeValorDeEstacionamento calculadora = new CalculadoraDeValorDeEstacionamento();
        BigDecimal valor = calculadora.calcular(estacionamento);
        estacionamento.setValor(valor);
        estacionamento.setAtivo(false);
        return new DadosEncerramentoEstacionamentoDTO(valor, true);
    }
}
