package com.saulo.webstore.repositories;

import com.saulo.webstore.models.Cupom;
import com.saulo.webstore.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Transactional(readOnly = true)
    Produto findByCodigo(String codigo);
}