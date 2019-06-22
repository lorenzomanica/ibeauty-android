package com.cursoandroid.danielmorsch.ibeauty.domain;


import java.io.Serializable;

public class Agendamento implements Serializable {


    private long codAgendamento;

    private long codEmpresa;

    private long codFuncionario;

    private Pessoa pessoa;


    private DadosPagamento dadosPagamento;


    private Servico servico;


    private Horario horario;


    private int estado;


    private Integer avaliacao;


    private String comentario;


    public long getCodAgendamento() {
        return codAgendamento;
    }

    public void setCodAgendamento(long codAgendamento) {
        this.codAgendamento = codAgendamento;
    }

    public long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public long getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(long codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public DadosPagamento getDadosPagamento() {
        return dadosPagamento;
    }

    public void setDadosPagamento(DadosPagamento dadosPagamento) {
        this.dadosPagamento = dadosPagamento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
