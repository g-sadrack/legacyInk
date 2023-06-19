package br.com.legacyink.domain.repository;

import br.com.legacyink.domain.model.Tatuador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TatuadorRepository extends JpaRepository<Tatuador, Long> {
}
