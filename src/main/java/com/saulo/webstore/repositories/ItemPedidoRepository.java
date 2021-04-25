package com.saulo.webstore.repositories;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}