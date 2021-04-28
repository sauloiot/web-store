package com.saulo.webstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Conta;
import com.saulo.webstore.models.Pedido;
import com.saulo.webstore.models.Produto;
import com.saulo.webstore.models.enums.TipoConta;
import com.saulo.webstore.repositories.CategoriaRepository;
import com.saulo.webstore.repositories.ContaRepository;
import com.saulo.webstore.repositories.ProdutoRepository;
import com.saulo.webstore.services.CategoriaService;
import com.saulo.webstore.services.ContaService;
import com.saulo.webstore.services.ProdutoService;
import com.saulo.webstore.utils.Utils;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(ProdutoController.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;
    @MockBean
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() throws Exception {

        Produto produto1 = new Produto(1, "Impressora", 300.00, "Impressora laser", Utils.code5L7N(), 0.00, "imagemURL", null);
        Produto produto2 = new Produto(2, "Impressora", 300.00, "Impressora laser", Utils.code5L7N(), 0.00, "imagemURL", null);

        List<Produto> produtos = Arrays.asList(produto1, produto2);
        given(produtoService.findAll()).willReturn(produtos);

        // when + then

        mockMvc.perform(get("/produtos")).andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void insert() throws Exception{
        Produto produto = new Produto(1, "Impressora", 300.00, "Impressora laser", Utils.code5L7N(), 0.00, "imagemURL", null);
        when(produtoService.insert(any())).thenReturn(produto);
        mockMvc.perform(post("/produtos").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(produto))).
                andExpect(status().isCreated());
        verify(produtoService,times(1)).insert(any());
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(delete("/produtos/{id}", 1))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}