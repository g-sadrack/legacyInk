package br.com.legacyink.domain.repository;

import br.com.legacyink.domain.model.Tatuagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TatuagemRepository extends JpaRepository<Tatuagem, Long> {

}
