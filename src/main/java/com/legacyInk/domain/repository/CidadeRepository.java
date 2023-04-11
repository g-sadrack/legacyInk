package com.legacyInk.domain.repository;

import com.legacyInk.domain.model.Cidade;
import com.legacyInk.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
