package com.saulo.webstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;
    private String descricao;
    private String codigo;
    private Double descontoPromocao;
    private String imagemURL;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria = new Categoria();

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<>();


//lista de imagens para futura implementação
//    @OneToMany(mappedBy="produto")
//    private List<Imagens> imagens = new ArrayList<>();

    public Produto() {
    }


    public Produto(Integer id, String nome, Double preco, String descricao, String codigo, Double descontoPromocao,String imagemURL, Categoria categoria) {
        super();
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.codigo = codigo;
        this.descontoPromocao = descontoPromocao;
        this.imagemURL = imagemURL;
        this.categoria = categoria;
    }

    @JsonIgnore
    public List<Pedido> getPedidos(){
        List<Pedido> lista = new ArrayList<>();
        for (ItemPedido x : itens){
            lista.add(x.getPedido());
        }
        return lista;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", descricao='" + descricao + '\'' +
                ", codigo='" + codigo + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
