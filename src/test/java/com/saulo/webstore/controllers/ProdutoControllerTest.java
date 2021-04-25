package com.saulo.webstore.controllers;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Conta;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    public void findById() throws Exception {
        when(produtoService.findById(1)).thenReturn(new Produto(1, "Impressora", 300.00, "Impressora laser", Utils.code5L7N(), null));
        mockMvc.perform( MockMvcRequestBuilders
                .get("/produtos/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Impressora"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preco").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.preco").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.preco").value(300.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value("Impressora laser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.codigo").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.codigo").isNotEmpty());

    }

}