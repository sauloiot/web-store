package com.saulo.webstore.models.enums;

public enum TipoConta {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String descricao;

    private TipoConta(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod(){
        return cod;
    }

    public String getDescricao(){
        return descricao;
    }

    public static TipoConta toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for (TipoConta x : TipoConta.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: "+ cod);
    }
}

