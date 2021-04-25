package com.saulo.webstore.models.enums;

public enum EstadoPagamento {
    PENDENTE(1, "PAGAMENTO_PENDENTE"),
    QUITADO(2, "PAGAMENTO_QUITADO"),
    CANCELADO(3, "PAGAMENTO_CANCELADO");

    private int cod;
    private String descricao;

    private EstadoPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod(){
        return cod;
    }

    public String getDescricao(){
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for (EstadoPagamento x : EstadoPagamento.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: "+ cod);
    }
}
