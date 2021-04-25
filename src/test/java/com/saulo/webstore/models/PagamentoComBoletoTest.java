package com.saulo.webstore.models;

import com.saulo.webstore.models.enums.EstadoPagamento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

class PagamentoComBoletoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testModelPagamentoComBoleto() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        PagamentoComBoleto pag = new PagamentoComBoleto(1, EstadoPagamento.PENDENTE, null, sdf.parse("23/04/2021 10:01"), null);

        assertThat(pag)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(1);
                    assertThat(u.getEstado()).isEqualTo(EstadoPagamento.PENDENTE);
                    assertThat(u.getPedido()).isEqualTo(null);
                    try {
                        assertThat(u.getDataVencimento()).isEqualTo(sdf.parse("23/04/2021 10:01"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    assertThat(u.getDataPagamento()).isEqualTo(null);
                });
    }
}