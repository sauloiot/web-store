package com.saulo.webstore.repositories;

import com.saulo.webstore.models.Pedido;
import com.saulo.webstore.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}