package com.saulo.webstore.models;

import com.saulo.webstore.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

class ItemPedidoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Test
    void testModelConta() throws ParseException {
        Produto produto = new Produto(1, "Impressora", 300.00, "Impressora laser", Utils.code5L7N(), 0.00,"imagemURL", null);
        Pedido pedido = new Pedido(1, "minha casa", sdf.parse("25/04/2021 00:41"), null);

        ItemPedidoPK itemPedidoPK = new ItemPedidoPK();
        itemPedidoPK.setProduto(produto);
        itemPedidoPK.setPedido(pedido);

        ItemPedido itemPedido = new ItemPedido(pedido, produto, 0.00, 3);

        assertThat(itemPedido)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(itemPedidoPK);
                    assertThat(u.getId().getPedido()).isEqualTo(pedido);
                    assertThat(u.getId().getProduto()).isEqualTo(produto);
                    assertThat(u.getDesconto()).isEqualTo(0.00);
                    assertThat(u.getQuantidade()).isEqualTo(3);
                });
    }
}