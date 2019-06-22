package com.cursoandroid.danielmorsch.ibeauty.domain;

import java.io.Serializable;
import java.util.Calendar;


public abstract class AuditModel implements Serializable {


    private Calendar dataCriacao;

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
