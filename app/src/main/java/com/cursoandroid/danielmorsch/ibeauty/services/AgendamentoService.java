package com.cursoandroid.danielmorsch.ibeauty.services;

import com.cursoandroid.danielmorsch.ibeauty.domain.Agendamento;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AgendamentoService {


    @POST("/agendamento")
    Call<Void> saveAgendamento(@Body Agendamento a);

    @GET("/agendamento/user/{id}/{pg}")
    Call<AgendamentoSvcResponse> getAgendamentosFromUserId(@Path("id") Long id, @Path("pg") Integer pg);

    @PUT("/agendamento/update")
    Call<Void> confirmAgendamento(@Body Agendamento agendamento);
}
