package com.saulo.webstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Conta;
import com.saulo.webstore.models.enums.TipoConta;
import com.saulo.webstore.repositories.CategoriaRepository;
import com.saulo.webstore.repositories.ContaRepository;
import com.saulo.webstore.services.CategoriaService;
import com.saulo.webstore.services.ContaService;
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

@WebMvcTest(ContaController.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaService contaService;
    @MockBean
    private ContaRepository contaRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() throws Exception {

        Conta conta1 = new Conta(1, "Administrador", "adm@hotmail.com", "0123456", TipoConta.ADMIN);
        Conta conta2 = new Conta(2, "Cliente", "cliente@hotmail.com", "0123456", TipoConta.CLIENTE);

        List<Conta> contas = Arrays.asList(conta1, conta2);
        given(contaService.findAll()).willReturn(contas);

        // when + then

        mockMvc.perform(get("/contas")).andExpect(status().isOk())
                .andExpect(content().json("[{\"id\": 1, \"nome\": \"Administrador\", \"email\": \"adm@hotmail.com\", \"tipoConta\": \"ADMIN\"}, {\"id\": 2, \"nome\": \"Cliente\", \"email\": \"cliente@hotmail.com\", \"tipoConta\": \"CLIENTE\"}]"));

    }

    @Test
    public void findById() throws Exception {
        when(contaService.findById(1)).thenReturn(new Conta(1, "Administrador", "adm@hotmail.com", "0123456", TipoConta.ADMIN));
        mockMvc.perform( MockMvcRequestBuilders
                .get("/contas/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Administrador"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("adm@hotmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoConta").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoConta").isNotEmpty());

    }

    @Test
    public void insert() throws Exception{
        Conta conta = new Conta(1, "Administrador", "adm@hotmail.com", "0123456", TipoConta.ADMIN);
        when(contaService.insert(any())).thenReturn(conta);
        mockMvc.perform(post("/contas").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(conta))).
                andExpect(status().isCreated());
        verify(contaService,times(1)).insert(any());
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(delete("/contas/{id}", 1))
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