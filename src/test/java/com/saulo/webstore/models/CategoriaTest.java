package com.saulo.webstore.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CategoriaTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testModelCategoria() {
        Categoria user = new Categoria(1, "Informatica");

        assertThat(user)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(1);
                    assertThat(u.getNome()).isEqualTo("Informatica");
                });
    }
}