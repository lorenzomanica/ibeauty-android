package com.cursoandroid.danielmorsch.ibeauty.services;

import com.cursoandroid.danielmorsch.ibeauty.domain.Credential;
import com.cursoandroid.danielmorsch.ibeauty.domain.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {


    @POST("/login")
    @Headers("Content-type: application/json")
    Call<Usuario> login(@Body Credential credential);


    @GET("/usuario/{id}")
    Call<Usuario> getUsuarioById(@Path("id") Long id);
}
