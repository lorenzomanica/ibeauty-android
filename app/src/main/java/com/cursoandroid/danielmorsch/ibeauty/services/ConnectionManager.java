package com.cursoandroid.danielmorsch.ibeauty.services;

import com.cursoandroid.danielmorsch.ibeauty.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ConnectionManager {


    private static ConnectionManager instance;


    public static ConnectionManager getInstance() {
        if (instance == null) instance = new ConnectionManager();
        return instance;
    }

    private ConnectionManager() {
        this.retrofit = getDefaultRetrofitInstance();
    }

    private Retrofit retrofit;


    public Retrofit getDefaultRetrofitInstance() {

        if (retrofit != null) return retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.APPLICATION_API_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit;
    }


    public ServicosService createServicosService() {
        return retrofit.create(ServicosService.class);
    }

    public HorariosService createHorariosService() {
        return retrofit.create(HorariosService.class);
    }

    public AgendamentoService createAgendamentoService() {
        return retrofit.create(AgendamentoService.class);
    }

    public UsuarioService createUsuarioService() {
        return retrofit.create(UsuarioService.class);
    }

    public FuncionarioService createFuncionarioService() {
        return retrofit.create(FuncionarioService.class);
    }
}
