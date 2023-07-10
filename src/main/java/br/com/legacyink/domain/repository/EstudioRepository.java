package br.com.legacyink.domain.repository;

import br.com.legacyink.domain.model.Estudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudioRepository extends JpaRepository<Estudio, Long> {
    Optional<Estudio> findByCnpj(String cnpj);

}
