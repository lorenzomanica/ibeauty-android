package com.cursoandroid.danielmorsch.ibeauty.services;

import com.cursoandroid.danielmorsch.ibeauty.domain.Funcionario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FuncionarioService {


    @GET("/funcionario/user/{id}")
    Call<Funcionario> getFuncionarioInfoByUserId(@Path("id") Long id);
}
