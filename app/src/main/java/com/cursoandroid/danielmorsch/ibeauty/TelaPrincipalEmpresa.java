package com.cursoandroid.danielmorsch.ibeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaPrincipalEmpresa extends AppCompatActivity {
    private TextView bemvindo;
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal_empresa);

        bemvindo = findViewById(R.id.bemvindo_empresa_id);
        Bundle extra = getIntent().getExtras();

        if(extra != null){
            //monta mensagem de boas vindas
            bemvindo.setText(bemvindo.getText().toString() + " " + extra.getString("usuario"));
        }
        //vai pra tela de agendamentos pendentes
        botao = findViewById(R.id.botao_meusagendamentos_id);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AgendamentosPendentes.class));
            }
        });
    }
}
