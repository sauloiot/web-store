package com.saulo.webstore.models;

import com.saulo.webstore.models.enums.EstadoPagamento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

class PagamentoComCartaoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testModelPagamentoComCartao() throws ParseException {
        PagamentoComCartao pag = new PagamentoComCartao(1, EstadoPagamento.QUITADO, null, 6);
        assertThat(pag)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(1);
                    assertThat(u.getEstado()).isEqualTo(EstadoPagamento.QUITADO);
                    assertThat(u.getPedido()).isEqualTo(null);
                    assertThat(u.getNumeroDeParcelas()).isEqualTo(6);
                });
    }
}