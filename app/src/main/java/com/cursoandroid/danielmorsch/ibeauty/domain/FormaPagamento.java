package com.cursoandroid.danielmorsch.ibeauty.domain;


import java.io.Serializable;

public class FormaPagamento implements Serializable {


    private long codFormaPgto;


    private String descricao;


    private boolean ativo;


    public long getCodFormaPgto() {
        return codFormaPgto;
    }

    public void setCodFormaPgto(long codFormaPgto) {
        this.codFormaPgto = codFormaPgto;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
