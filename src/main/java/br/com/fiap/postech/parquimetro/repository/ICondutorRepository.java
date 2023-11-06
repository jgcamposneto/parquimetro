package br.com.fiap.postech.parquimetro.repository;

import br.com.fiap.postech.parquimetro.domain.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICondutorRepository extends JpaRepository<Condutor, UUID> {
}
