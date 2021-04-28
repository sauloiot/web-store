package com.saulo.webstore.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Cupom implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigo;
    private Double desconto;
    private Integer tipo;

    public Cupom() {
    }

    public Cupom(Integer id, String codigo, Double desconto, Integer tipo) {
        this.id = id;
        this.codigo = codigo;
        this.desconto = desconto;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cupom cupom = (Cupom) o;
        return id.equals(cupom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
