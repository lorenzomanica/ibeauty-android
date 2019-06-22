package com.cursoandroid.danielmorsch.ibeauty.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServicosService {


    @GET("/servicos/{pg}")
    Call<ServicosSvcResponse> getServicosPaginated(@Path("pg") int page);
}
