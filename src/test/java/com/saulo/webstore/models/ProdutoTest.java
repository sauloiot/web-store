package com.saulo.webstore.models;

import com.saulo.webstore.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ProdutoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testModelProduto() {
        Produto produto = new Produto(1, "Mouse", 100.00, "Mouse sem fio", Utils.code5L7N(), 0.00, "imagemURL", null);

        assertThat(produto)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(1);
                    assertThat(u.getNome()).isEqualTo("Mouse");
                    assertThat(u.getPreco()).isEqualTo(100.00);
                    assertThat(u.getDescricao()).isEqualTo("Mouse sem fio");
                    assertThat(u.getCodigo()).isNotNull();
                    assertThat(u.getCategoria()).isEqualTo(null);
                });
    }
}