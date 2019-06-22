package com.cursoandroid.danielmorsch.ibeauty.domain;

import java.io.Serializable;

public class Servico extends AuditModel implements Serializable {


    private long codServico;


    private Empresa empresa;


    private String nome;


    private String preco;


    private boolean ativo;


    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public long getCodServico() {
        return codServico;
    }

    public void setCodServico(long codServico) {
        this.codServico = codServico;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }


    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }

}
