package com.saulo.webstore.repositories;

import com.saulo.webstore.models.Categoria;
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
class CategoriaRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private CategoriaRepository repository;
    @Test
    void testSave() {
        Categoria cat = new Categoria(1, "Informatica");
        Categoria categoria = repository.save(cat);
        assertThat(categoria).isNotNull();
    }
    @Test
    void testFindById(){
        Optional<Categoria> accUser = repository.findById(1);
        assertThat(accUser).isNotNull();
    }
    @Test
    void testFindAll(){
        List<Categoria> accUser = repository.findAll();
        assertThat(accUser).isNotNull();
    }
    @Test
    void testDeleteById(){
        Categoria cat = new Categoria(1, "Informatica");
        Categoria categoria = repository.save(cat);
        repository.deleteById(categoria.getId());
    }
}