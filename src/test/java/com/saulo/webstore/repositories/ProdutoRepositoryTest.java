package com.saulo.webstore.repositories;

import com.saulo.webstore.models.Produto;
import com.saulo.webstore.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ProdutoRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private ProdutoRepository repository;
    @Test
    void testSave() {
        Produto produto = new Produto(1, "Mouse", 100.00, "Mouse sem fio", Utils.code5L7N(), 0.00, null);
        Produto produto1 = repository.save(produto);
        assertThat(produto1).isNotNull();
    }
    @Test
    void testFindById(){
        Optional<Produto> produto = repository.findById(1);
        assertThat(produto).isNotNull();
    }
    @Test
    void testFindAll(){
        List<Produto> produto = repository.findAll();
        assertThat(produto).isNotNull();
    }
    @Test
    void testDeleteById(){
        Produto produto = new Produto(1, "Mouse", 100.00, "Mouse sem fio", Utils.code5L7N(), 0.00, null);
        Produto produto1 = repository.save(produto);
        repository.deleteById(produto1.getId());
    }
}