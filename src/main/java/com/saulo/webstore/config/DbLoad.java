package com.saulo.webstore.config;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Profile("test")
@Configuration
public class DbLoad implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "INFORMATICA");
        Categoria cat2 = new Categoria(null, "ESCRITORIO");

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));



    }
}
