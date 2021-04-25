package com.saulo.webstore.services;

import com.saulo.webstore.models.Pedido;
import com.saulo.webstore.models.Pedido;
import com.saulo.webstore.repositories.CategoriaRepository;
import com.saulo.webstore.repositories.PedidoRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class PedidoServiceTest {

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @InjectMocks
    PedidoService pedidoService;

    @Mock
    PedidoRepository pedidoRepository;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Test
    public void findById() throws ParseException {

        when(pedidoRepository.findById(1)).thenReturn(java.util.Optional.of(new Pedido(1, "minha casa", sdf.parse("25/04/2021 00:41"), null)));

        Pedido emp = pedidoService.findById(1);
        assertEquals(1, emp.getId());
        assertEquals("minha casa", emp.getEnderecoEntrega());
    }

    @Test
    public void findAll() throws ParseException {
        List<Pedido> list = new ArrayList<Pedido>();
        Pedido empOne = new Pedido(1, "minha casa", sdf.parse("25/04/2021 00:41"), null);
        Pedido empTwo = new Pedido(2, "minha casa", sdf.parse("25/04/2021 00:41"), null);
        Pedido empThree = new Pedido(3, "minha casa", sdf.parse("25/04/2021 00:41"), null);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(pedidoRepository.findAll()).thenReturn(list);

        //test
        List<Pedido> empList = pedidoService.findAll();

        assertEquals(3, empList.size());
        verify(pedidoRepository, times(1)).findAll();
    }
    
    @Test
    public void insert() throws ParseException {
        Pedido categoria = new Pedido(1, "minha casa", sdf.parse("25/04/2021 00:41"), null);
        pedidoService.insert(categoria);
        verify(pedidoRepository, times(1)).save(categoria);
    }
}