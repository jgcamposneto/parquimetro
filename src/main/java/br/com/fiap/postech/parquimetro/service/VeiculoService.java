package br.com.fiap.postech.parquimetro.service;

import br.com.fiap.postech.parquimetro.service.exception.ControllerNotFoundException;
import br.com.fiap.postech.parquimetro.dominio.Veiculo;
import br.com.fiap.postech.parquimetro.repository.IVeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {

    @Autowired
    private IVeiculoRepository repository;

    public Page<Veiculo> findAll(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public Veiculo findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Veículo não encontrado"));
    }

    public Veiculo save(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public Veiculo update(UUID id, Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public void delete(UUID id) {
            repository.deleteById(id);
    }

}
