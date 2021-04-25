package com.saulo.webstore.repositories;

import com.saulo.webstore.models.*;
import com.saulo.webstore.models.enums.EstadoPagamento;
import com.saulo.webstore.models.enums.TipoConta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class PagamentoRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private PagamentoRepository repository;

    @Test
    void testFindById(){
        Optional<Pagamento> pagamento = repository.findById(1);
        assertThat(pagamento).isNotNull();
    }
    @Test
    void testFindAll(){
        List<Pagamento> pagamentos = repository.findAll();
        assertThat(pagamentos).isNotNull();
    }

}