package br.com.fiap.postech.parquimetro.repository;

import br.com.fiap.postech.parquimetro.dominio.Estacionamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IEstacionamentoRepository extends JpaRepository<Estacionamento, UUID> {
    Page<Estacionamento> findAll(Pageable paginacao);

    List<Estacionamento> findByAtivoTrue();
}
