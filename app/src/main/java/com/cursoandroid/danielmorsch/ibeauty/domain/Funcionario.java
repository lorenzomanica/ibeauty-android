package com.cursoandroid.danielmorsch.ibeauty.domain;


import java.io.Serializable;

public class Funcionario implements Serializable {


    private long codFuncionario;


    private Usuario usuario;


    private Empresa empresa;


    private String papel;


    private boolean ativo;


    public long getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(long codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
