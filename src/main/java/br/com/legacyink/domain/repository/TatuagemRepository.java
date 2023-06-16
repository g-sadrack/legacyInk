package br.com.legacyink.domain.repository;

import br.com.legacyink.domain.model.Tatuagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TatuagemRepository extends JpaRepository<Tatuagem, Long> {

}
