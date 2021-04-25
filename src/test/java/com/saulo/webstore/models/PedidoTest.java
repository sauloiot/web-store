package com.saulo.webstore.models;

import com.saulo.webstore.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

class PedidoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testModelPedido() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido pedido = new Pedido(1, "minha casa", sdf.parse("25/04/2021 00:41"), null);

        assertThat(pedido)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(1);
                    assertThat(u.getEnderecoEntrega()).isEqualTo("minha casa");
                    try {
                        assertThat(u.getInstante()).isEqualTo(sdf.parse("25/04/2021 00:41"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    assertThat(u.getConta()).isEqualTo(null);
                });
    }
}