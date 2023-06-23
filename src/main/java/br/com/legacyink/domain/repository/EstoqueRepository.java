package br.com.legacyink.domain.repository;


import br.com.legacyink.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Item, Long> {

}
