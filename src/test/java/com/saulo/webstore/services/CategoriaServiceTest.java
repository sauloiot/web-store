package com.saulo.webstore.services;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.repositories.CategoriaRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


class CategoriaServiceTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @InjectMocks
    CategoriaService categoriaService;

    @Mock
    CategoriaRepository categoriaRepository;

    @Test
    public void find() {

        when(categoriaRepository.findById(1)).thenReturn(java.util.Optional.of(new Categoria(1, "Informatica")));

        Categoria emp = categoriaService.find(1);
        assertEquals(1, emp.getId());
        assertEquals("Informatica", emp.getNome());

    }

    @Test
    public void findAll()
    {
        List<Categoria> list = new ArrayList<Categoria>();
        Categoria empOne = new Categoria(1, "Informatica");
        Categoria empTwo = new Categoria(1, "Beleza");
        Categoria empThree = new Categoria(1, "Cozinha");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(categoriaRepository.findAll()).thenReturn(list);

        //test
        List<Categoria> empList = categoriaService.findAll();

        assertEquals(3, empList.size());
        verify(categoriaRepository, times(1)).findAll();
    }


    @Test
    public void save(){
        Categoria categoria = new Categoria(1, "Informatica");
        categoriaService.insert(categoria);
        verify(categoriaRepository, times(1)).save(categoria);
    }
}