package com.saulo.webstore.models;

import com.saulo.webstore.models.enums.TipoConta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ContaTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testModelConta() {
        Conta conta = new Conta(1, "Administrador", "adm@hotmail.com", "0123456", TipoConta.ADMIN);

        assertThat(conta)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(1);
                    assertThat(u.getNome()).isEqualTo("Administrador");
                    assertThat(u.getEmail()).isEqualTo("adm@hotmail.com");
                    assertThat(u.getSenha()).isEqualTo("0123456");
                    assertThat(u.getTipoConta()).isEqualTo(TipoConta.ADMIN);
                });
    }
}