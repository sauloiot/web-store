package com.saulo.webstore.dtos;

import com.saulo.webstore.models.Cupom;
import com.saulo.webstore.services.validation.CupomInsert;

import java.io.Serializable;

@CupomInsert
public class CupomDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String codigo;
    private Double desconto;
    private Integer tipo;

    public CupomDTO() {
    }
    public CupomDTO(Cupom desc) {
        super();
        id = desc.getId();
        codigo = desc.getCodigo();
        desconto = desc.getDesconto();
        tipo = desc.getTipo();
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
}
