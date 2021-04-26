package com.saulo.webstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saulo.webstore.dtos.CategoriaDTO;
import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Produto;
import com.saulo.webstore.repositories.CategoriaRepository;
import com.saulo.webstore.services.CategoriaService;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
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

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CategoriaController.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;
    @MockBean
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaController categoriaController;


    @BeforeEach
    void setUp() {
//        categoria01 = new Categoria(1, "Informatica");
//        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() throws Exception {

        Categoria categoria1 = new Categoria(1, "INFORMATICA");
        Categoria categoria2 = new Categoria(2, "ESCRITORIO");

        List<Categoria> departments = Arrays.asList(categoria1, categoria2);
        given(categoriaService.findAll()).willReturn(departments);

        // when + then

        mockMvc.perform(get("/categorias")).andExpect(status().isOk())
                .andExpect(content().json("[{\"id\": 1, \"nome\": \"INFORMATICA\"}, {\"id\": 2, \"nome\": \"ESCRITORIO\"}]"));

    }



    @Test
    public void findById() throws Exception {
        when(categoriaService.findById(1)).thenReturn(new Categoria(1, "Informatica"));
        mockMvc.perform( MockMvcRequestBuilders
                .get("/categorias/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Informatica"));

    }

    @Test
    public void insert() throws Exception{
        Categoria categoria = new Categoria(1, "Informatica");
        when(categoriaService.insert(any())).thenReturn(categoria);
        mockMvc.perform(post("/categorias").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(categoria))).
                andExpect(status().isCreated());
                verify(categoriaService,times(1)).insert(any());
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(delete("/categorias/{id}", 1))
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