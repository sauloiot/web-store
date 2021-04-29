package com.saulo.webstore.services;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Conta;
import com.saulo.webstore.models.enums.TipoConta;
import com.saulo.webstore.repositories.CategoriaRepository;
import com.saulo.webstore.repositories.ContaRepository;
import com.saulo.webstore.repositories.ProdutoRepository;
import com.saulo.webstore.services.exceptions.AuthorizationException;
import com.saulo.webstore.utils.Utils;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class ContaServiceTest {

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @InjectMocks
    ContaService produtoService;

    @Mock
    ContaRepository contaRepository;

    @Test
    public void findById() {
        when(contaRepository.findById(1)).thenReturn(java.util.Optional.of(new Conta(1, "Administrador", "adm@hotmail.com", "0123456", TipoConta.ADMIN)));

        try {
            Conta conta = produtoService.findById(1);
            assertEquals(1, conta.getId());
            assertEquals("Administrador", conta.getNome());
            assertEquals("adm@hotmail.com", conta.getEmail());
            assertEquals("0123456", conta.getSenha());
            assertEquals(TipoConta.ADMIN, conta.getTipoConta());
        }catch (AuthorizationException e){
            e.getCause();
        }

    }

    @Test
    public void findAll()
    {
        List<Conta> list = new ArrayList<Conta>();
        Conta conta1 = new Conta(1, "Administrador", "adm@hotmail.com", "0123456", TipoConta.ADMIN);
        Conta conta2 = new Conta(2, "Administrador", "adm2@hotmail.com", "01234567", TipoConta.ADMIN);
        Conta conta3 = new Conta(3, "Administrador", "adm3@hotmail.com", "01234568", TipoConta.ADMIN);

        list.add(conta1);
        list.add(conta2);
        list.add(conta3);

        when(contaRepository.findAll()).thenReturn(list);

        //test
        List<Conta> contaList = produtoService.findAll();

        assertEquals(3, contaList.size());
        verify(contaRepository, times(1)).findAll();
    }

    @Test
    public void insert(){
        Conta conta = new Conta(1, "Administrador", "adm@hotmail.com", "0123456", TipoConta.ADMIN);
        produtoService.insert(conta);
        verify(contaRepository, times(1)).save(conta);
    }
}