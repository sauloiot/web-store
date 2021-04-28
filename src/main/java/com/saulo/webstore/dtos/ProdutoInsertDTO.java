package com.saulo.webstore.dtos;

import com.saulo.webstore.models.Produto;

import java.io.Serializable;

public class ProdutoInsertDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private Double preco;
    private String descricao;
    private Double descontoPromocao;
    private String imagemURL;
    private Integer idCategoria;

    public ProdutoInsertDTO() {
    }

    public ProdutoInsertDTO(Produto produto) {
        super();
        id = produto.getId();
        nome = produto.getNome();
        preco = produto.getPreco();
        descricao = produto.getDescricao();
        descontoPromocao = produto.getDescontoPromocao();
        imagemURL = produto.getImagemURL();
        idCategoria = produto.getCategoria().getId();

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

    public String getImagemURL() {
        return imagemURL;
    }

    public void setImagemURL(String imagemURL) {
        this.imagemURL = imagemURL;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}
