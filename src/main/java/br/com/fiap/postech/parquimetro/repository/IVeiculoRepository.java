package br.com.fiap.postech.parquimetro.repository;

import br.com.fiap.postech.parquimetro.dominio.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IVeiculoRepository extends JpaRepository<Veiculo, UUID> {

    Page<Veiculo> findAll(Pageable paginacao);
}
