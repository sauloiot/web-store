package com.saulo.webstore.dtos;

import com.saulo.webstore.dtos.converter.CategoriaDTOConverter;
import com.saulo.webstore.models.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private Double preco;
    private String descricao;
    private Double descontoPromocao;
    private CategoriaDTO categoria;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        super();
        id = produto.getId();
        nome = produto.getNome();
        preco = produto.getPreco();
        descricao = produto.getDescricao();
        descontoPromocao = produto.getDescontoPromocao();
        categoria = CategoriaDTOConverter.entityToDTO(produto.getCategoria());

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getDescontoPromocao() {
        return descontoPromocao;
    }

    public void setDescontoPromocao(Double descontoPromocao) {
        this.descontoPromocao = descontoPromocao;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
}
