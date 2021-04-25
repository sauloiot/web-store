package com.saulo.webstore.repositories;

import com.saulo.webstore.models.Pagamento;
import com.saulo.webstore.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}