package com.saulo.webstore.config;

import com.saulo.webstore.models.Categoria;
import com.saulo.webstore.models.Produto;
import com.saulo.webstore.repositories.CategoriaRepository;
import com.saulo.webstore.repositories.ProdutoRepository;
import com.saulo.webstore.utils.Utils;
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
    @Autowired
    private ProdutoRepository produtoRepository;


    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "INFORMATICA");
        Categoria cat2 = new Categoria(null, "ESCRITORIO");

        Produto produto1 = new Produto(null, "Impressora", 300.00, "Impressora laser", Utils.code5L7N(), cat1);
        Produto produto2 = new Produto(null, "Mouse", 100.00, "Mouse sem fio", Utils.code5L7N(), cat1);
        Produto produto3 = new Produto(null, "Mesa", 400.00, "Mesa grande", Utils.code5L7N(), cat2);
        Produto produto4 = new Produto(null, "Cadeira", 250.00, "Cadeira de rodinhas", Utils.code5L7N(), cat2);

        cat1.getProdutos().addAll(Arrays.asList(produto1, produto2));
        cat2.getProdutos().addAll(Arrays.asList(produto3, produto4));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3,produto4));




    }
}
