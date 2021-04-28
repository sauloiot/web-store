package com.saulo.webstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Conta;
import com.saulo.webstore.models.Pedido;
import com.saulo.webstore.models.Pedido;
import com.saulo.webstore.models.enums.TipoConta;
import com.saulo.webstore.repositories.CategoriaRepository;
import com.saulo.webstore.repositories.ContaRepository;
import com.saulo.webstore.repositories.PedidoRepository;
import com.saulo.webstore.services.CategoriaService;
import com.saulo.webstore.services.ContaService;
import com.saulo.webstore.services.PedidoService;
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

import java.text.SimpleDateFormat;
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

@WebMvcTest(PedidoController.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;
    @MockBean
//    private ContaService contaService;
//    @MockBean
    private PedidoRepository pedidoRepository;
//    @MockBean
//    private ContaRepository contaRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

//    @Test
//    void findAll() throws Exception {
//
//        Pedido pedido1 = new Pedido(1, "minha casa", sdf.parse("24/04/2021 05:32"), null);
//        Pedido pedido2 = new Pedido(2, "minha casa", sdf.parse("24/04/2021 05:32"), null);
//
//        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);
//        given(pedidoService.findAll()).willReturn(pedidos);
//
//        // when + then
//
//        mockMvc.perform(get("/pedidos")).andExpect(status().isOk())
//                .andExpect(content()
//                .contentType(MediaType.APPLICATION_JSON));
//
//    }
//
//    @Test
//    public void findById() throws Exception {
//
//        when(pedidoService.findById(1)).thenReturn(new Pedido(1, "minha casa", sdf.parse("24/04/2021 05:32"), null));
//        mockMvc.perform( MockMvcRequestBuilders
//                .get("/pedidos/{id}", 1)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecoEntrega").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecoEntrega").isNotEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecoEntrega").value("minha casa"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.instante").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.instante").isNotEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.instante").isString());
//
//    }
//
//    @Test
//    public void insert() throws Exception{
//        Pedido pedido = new Pedido(1, "minha casa", sdf.parse("24/04/2021 05:32"), null);
//        when(pedidoService.insert(any())).thenReturn(pedido);
//        mockMvc.perform(post("/pedidos").
//                contentType(MediaType.APPLICATION_JSON).
//                content(asJsonString(pedido))).
//                andExpect(status().isCreated());
//        verify(pedidoService,times(1)).insert(any());
//    }
//
//    @Test
//    public void deleteById() throws Exception {
//        mockMvc.perform(delete("/pedidos/{id}", 1))
//                .andExpect(status().isNoContent());
//    }

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