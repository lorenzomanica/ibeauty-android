package com.cursoandroid.danielmorsch.ibeauty.domain;


import java.io.Serializable;
import java.util.Objects;


public class Usuario extends AuditModel implements Serializable {

    private long codUsuario;


    private String usuario;


    private String senha;


    private boolean ativo;


    public long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(long codUsuario) {
        this.codUsuario = codUsuario;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    public String toString() {
        return "Usuario{" +
                "codUsuario=" + codUsuario +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                ", ativo='" + ativo + '\'' +
                '}';
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return codUsuario == usuario1.codUsuario &&
                usuario.equals(usuario1.usuario) &&
                senha.equals(usuario1.senha) &&
                ativo == usuario1.ativo;
    }


    public int hashCode() {
        return Objects.hash(codUsuario, usuario, senha, ativo);
    }
}
