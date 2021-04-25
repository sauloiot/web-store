package com.saulo.webstore.config;

import com.saulo.webstore.models.*;
import com.saulo.webstore.models.enums.EstadoPagamento;
import com.saulo.webstore.models.enums.TipoConta;
import com.saulo.webstore.repositories.*;
import com.saulo.webstore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Profile("test")
@Configuration
public class DbLoad implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;



    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "INFORMATICA");
        Categoria cat2 = new Categoria(null, "ESCRITORIO");

        Produto produto1 = new Produto(null, "Impressora", 300.00, "Impressora laser", Utils.code5L7N(), cat1);
        Produto produto2 = new Produto(null, "Mouse", 100.00, "Mouse sem fio", Utils.code5L7N(), cat1);
        Produto produto3 = new Produto(null, "Mesa", 400.00, "Mesa grande", Utils.code5L7N(), cat2);
        Produto produto4 = new Produto(null, "Cadeira", 250.00, "Cadeira de rodinhas", Utils.code5L7N(), cat2);

        Conta conta1 = new Conta(null, "Administrador", "adm@hotmail.com", "0123456", TipoConta.ADMIN);
        Conta conta2 = new Conta(null, "Cliente", "cliente@hotmail.com", "0123456", TipoConta.CLIENTE);

        cat1.getProdutos().addAll(Arrays.asList(produto1, produto2));
        cat2.getProdutos().addAll(Arrays.asList(produto3, produto4));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3,produto4));
        contaRepository.saveAll(Arrays.asList(conta1,conta2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido pedido1 = new Pedido(null, "minha casa", sdf.parse("25/04/2021 00:41"), conta1);
        Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
        pedido1.setPagamento(pag1);
        conta1.getPedidos().addAll(Arrays.asList(pedido1));

        Pedido pedido2 = new Pedido(null, "minha casa", sdf.parse("24/04/2021 05:32"), conta2);
        Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("23/04/2021 10:01"), null);
        pedido2.setPagamento(pag2);
        conta2.getPedidos().addAll(Arrays.asList(pedido2));

        pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
        pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));







    }
}
