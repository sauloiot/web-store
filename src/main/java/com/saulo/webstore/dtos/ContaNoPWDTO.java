package com.saulo.webstore.dtos;

import com.saulo.webstore.models.Conta;
import com.saulo.webstore.models.enums.TipoConta;

import java.io.Serializable;

public class ContaNoPWDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private Integer tipoConta;

    public ContaNoPWDTO() {
    }

    public ContaNoPWDTO(Conta conta) {
        id = conta.getId();
        nome = conta.getNome();
        email = conta.getEmail();
        tipoConta = conta.getTipoConta().getCod();
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

    public TipoConta getTipoConta() {
        return TipoConta.toEnum(tipoConta);
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta.getCod();
    }

}
