package com.cursoandroid.danielmorsch.ibeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button botao;
    private TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.texto_email_id);
        botao = findViewById(R.id.button_login_id);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                //pega o dominio do email
                String tipo = texto.getText().toString().split("@")[1];
                //se o dominio conter ibeauty, inicia a tela de empresa
                if(tipo.contains("ibeauty")){
                    intent = new Intent(getApplicationContext(), TelaPrincipalEmpresa.class);
                    intent.putExtra("usuario", texto.getText().toString().split("@")[0]);
                    startActivity(intent);
                }else{
                    //tela do cliente
                    intent = new Intent(getApplicationContext(), TelaPrincipal.class);
                    intent.putExtra("usuario", texto.getText().toString().split("@")[0]);
                    startActivity(intent);
                }
            }
        });
    }
}
