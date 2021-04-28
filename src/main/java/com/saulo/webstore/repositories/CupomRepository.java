package com.saulo.webstore.repositories;

import com.saulo.webstore.models.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer> {
    @Transactional(readOnly = true)
    Cupom findByCodigo(String codigo);
}