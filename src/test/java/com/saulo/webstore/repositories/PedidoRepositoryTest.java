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
class PedidoRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private PedidoRepository pedidoRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Test
    void testSave() throws ParseException {
        Pedido pedido = new Pedido(null, "minha casa", sdf.parse("25/04/2021 00:41"), null);
        Pedido ped1 = pedidoRepository.save(pedido);
        assertThat(ped1).isNotNull();
    }
    @Test
    void testFindById(){
        Optional<Pedido> pedido = pedidoRepository.findById(1);
        assertThat(pedido).isNotNull();
    }
    @Test
    void testFindAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        assertThat(pedidos).isNotNull();
    }
    @Test
    void testDeleteById() throws ParseException {
        Pedido pedido = new Pedido(null, "minha casa", sdf.parse("25/04/2021 00:41"), null);
        Pedido pedido1 = pedidoRepository.save(pedido);
        pedidoRepository.deleteById(pedido1.getId());
    }
}