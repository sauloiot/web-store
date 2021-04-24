package com.saulo.webstore.repositories;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Conta;
import com.saulo.webstore.models.enums.TipoConta;
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
class ContaRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private ContaRepository repository;
    @Test
    void testSave() {
        Conta conta = new Conta(1, "Administrador", "adm@hotmail.com", "0123456", TipoConta.ADMIN);
        Conta conta1 = repository.save(conta);
        assertThat(conta1).isNotNull();
    }
    @Test
    void testFindById(){
        Optional<Conta> conta = repository.findById(1);
        assertThat(conta).isNotNull();
    }
    @Test
    void testFindAll(){
        List<Conta> contas = repository.findAll();
        assertThat(contas).isNotNull();
    }
    @Test
    void testDeleteById(){
        Conta conta = new Conta(1, "Administrador", "adm@hotmail.com", "0123456", TipoConta.ADMIN);
        Conta conta1 = repository.save(conta);
        repository.deleteById(conta1.getId());
    }
}