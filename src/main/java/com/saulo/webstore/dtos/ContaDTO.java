package com.saulo.webstore.dtos;

import com.saulo.webstore.models.Conta;
import com.saulo.webstore.models.enums.TipoConta;
import com.saulo.webstore.services.validation.ContaInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ContaInsert
public class ContaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 2, max = 150, message = "O tamanho deve ser entre 2 e 150 caracteres")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Email(message = "Email invalido")
    private String email;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 5, max = 150, message = "O tamanho deve ser entre 2 e 150 caracteres")
    private String senha;
    private Integer tipoConta;

    public ContaDTO() {
    }

    public ContaDTO(Conta conta) {
        id = conta.getId();
        nome = conta.getNome();
        email = conta.getEmail();
        senha = conta.getSenha();
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
}
