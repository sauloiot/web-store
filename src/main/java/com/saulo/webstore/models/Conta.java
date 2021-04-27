package com.saulo.webstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saulo.webstore.models.enums.TipoConta;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    private Integer tipoConta;

    @OneToMany(mappedBy = "conta")
    private List<Pedido> pedidos = new ArrayList<>();

    public Conta() {
    }

    public Conta(Integer id, String nome, String email, String senha, TipoConta tipoConta) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoConta = tipoConta.getCod();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoConta getTipoConta() {
        return TipoConta.toEnum(tipoConta);
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta.getCod();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    @JsonIgnore
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return id.equals(conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", tipoConta=" + tipoConta +
                '}';
    }
}
