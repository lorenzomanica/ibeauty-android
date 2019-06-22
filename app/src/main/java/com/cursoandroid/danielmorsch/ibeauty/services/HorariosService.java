package com.cursoandroid.danielmorsch.ibeauty.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HorariosService {


    @GET("/horarios/empresa/{e}/{t}/{p}")
    Call<HorarioSvcResponse> getHorariosFromEmpresaWithTime(@Path("e") Long emp, @Path("t") Long time, @Path("p") Integer pg);
}
