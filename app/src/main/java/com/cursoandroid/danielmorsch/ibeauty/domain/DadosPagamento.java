package com.cursoandroid.danielmorsch.ibeauty.domain;


import java.io.Serializable;

public class DadosPagamento extends AuditModel implements Serializable {


    private long codDadoPagto;


    private Pessoa codPessoa;


    private FormaPagamento codFormaPgto;


    private String nome;


    private String numero;


    public long getCodDadoPagto() {
        return codDadoPagto;
    }

    public void setCodDadoPagto(long codDadoPagto) {
        this.codDadoPagto = codDadoPagto;
    }

    public Pessoa getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Pessoa codPessoa) {
        this.codPessoa = codPessoa;
    }

    public FormaPagamento getCodFormaPgto() {
        return codFormaPgto;
    }

    public void setCodFormaPgto(FormaPagamento codFormaPgto) {
        this.codFormaPgto = codFormaPgto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
