package br.com.legacyink.domain.repository;

import br.com.legacyink.domain.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Optional<Agendamento> findByCodigo(String codigo);

}