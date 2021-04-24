package com.saulo.webstore.services;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Produto;
import com.saulo.webstore.repositories.CategoriaRepository;
import com.saulo.webstore.repositories.ProdutoRepository;
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
class ProdutoServiceTest {

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @InjectMocks
    ProdutoService produtoService;

    @Mock
    ProdutoRepository produtoRepository;

    @Test
    public void findById() {
        when(produtoRepository.findById(1)).thenReturn(java.util.Optional.of(new Produto(1, "Impressora", 300.00, "Impressora laser", Utils.code5L7N(), null)));

        Produto produto = produtoService.findById(1);
        assertEquals(1, produto.getId());
        assertEquals("Impressora", produto.getNome());
        assertEquals(300.00, produto.getPreco());
        assertEquals("Impressora laser", produto.getDescricao());
        assertEquals(null, produto.getCategoria());
    }

    @Test
    public void findAll()
    {
        List<Produto> list = new ArrayList<Produto>();
        Produto prod1 = new Produto(1, "Impressora", 300.00, "Impressora laser", Utils.code5L7N(), null);
        Produto prod2 = new Produto(2, "Mouse", 100.00, "Mouse sem fio", Utils.code5L7N(), null);
        Produto prod3 = new Produto(3, "Teclado", 200.00, "Teclado sem fio", Utils.code5L7N(), null);

        list.add(prod1);
        list.add(prod2);
        list.add(prod3);

        when(produtoRepository.findAll()).thenReturn(list);

        List<Produto> prodList = produtoService.findAll();

        assertEquals(3, prodList.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    public void insert(){
        Produto produto = new Produto(1, "Impressora", 300.00, "Impressora laser", Utils.code5L7N(), null);
        produtoService.insert(produto);
        verify(produtoRepository, times(1)).save(produto);
    }
}