package com.cursoandroid.danielmorsch.ibeauty.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Horario implements Serializable {


    private long codHorario;

    private Funcionario funcionario;


    private Empresa empresa;


    private String dataHora;


    private boolean reservado;


    public long getCodHorario() {
        return codHorario;
    }

    public void setCodHorario(long codHorario) {
        this.codHorario = codHorario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
}
