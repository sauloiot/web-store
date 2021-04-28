package com.saulo.webstore.repositories;

import com.saulo.webstore.models.*;
import com.saulo.webstore.models.enums.TipoConta;
import com.saulo.webstore.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ItemPedidoRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private ItemPedidoRepository repository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Test
    void testSave() throws ParseException {
        Produto produto = new Produto(1, "Mouse", 100.00, "Mouse sem fio", Utils.code5L7N(), 0.00, "imagemURL",null);
        Pedido pedido = new Pedido(1, "minha casa", sdf.parse("25/04/2021 00:41"), null);
        ItemPedido itemPedido = new ItemPedido(pedido, produto, 50.00, 10);
        ItemPedido itemPedido1 = repository.save(itemPedido);
        assertThat(itemPedido1).isNotNull();
    }
    @Test
    void testFindAll(){
        List<ItemPedido> contas = repository.findAll();
        assertThat(contas).isNotNull();
    }
}