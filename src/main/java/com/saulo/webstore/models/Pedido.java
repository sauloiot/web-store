package com.saulo.webstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String enderecoEntrega;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instante;
    private Double valorTotal;
    private String cupom;
    private Double valorComCupom;
    private Double valorTotalAVista10PorcDesc;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Conta conta;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido() {
    }

    public Pedido(Integer id, String enderecoEntrega, Date instante, Conta conta) {
        this.id = id;
        this.enderecoEntrega = enderecoEntrega;
        this.instante = instante;
        this.conta = conta;
    }

    public Pedido(Integer id, String enderecoEntrega, Date instante, String cupom, Conta conta) {
        this.id = id;
        this.enderecoEntrega = enderecoEntrega;
        this.instante = instante;
        this.cupom = cupom;
        this.conta = conta;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public Double getValorTotalAVista10PorcDesc() {
        return valorTotalAVista10PorcDesc;
    }

    public void setValorTotalAVista10PorcDesc(Double valorTotalAVista10PorcDesc) {
        this.valorTotalAVista10PorcDesc = valorTotalAVista10PorcDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorComCupom() {
        return valorComCupom;
    }

    public void setValorComCupom(Double valorComCupom) {
        this.valorComCupom = valorComCupom;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", enderecoEntrega='" + enderecoEntrega + '\'' +
                ", instante=" + instante +
                ", pagamento=" + pagamento +
                ", conta=" + conta +
                '}';
    }
}
