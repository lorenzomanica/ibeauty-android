package com.cursoandroid.danielmorsch.ibeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DetalheEmpresa extends AppCompatActivity {
    private TextView nomeempresa;
    private Spinner servicos, horarios;
    private Button botao;
    private String[] listahorarios = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_empresa);

        nomeempresa = findViewById(R.id.nome_empresa_id);
        Bundle extra = getIntent().getExtras();

        if(extra != null){
            //Coloca o nome da empresa no topo da tela
            nomeempresa.setText(extra.getString("empresa"));
        }
        //monta spinner com servicos disponiveis
        servicos = findViewById(R.id.spinner_servicos_id);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nomeservicos));
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        servicos.setAdapter(adaptador);
        //monta spinner com horarios disponiveis
        horarios = findViewById(R.id.spinner_horarios_id);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, listahorarios);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        horarios.setAdapter(adapt);
        //pega os valores selecionados e volta pra tela principal
        botao = findViewById(R.id.botao_agendar_id);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valorspinners = servicos.getSelectedItem().toString() + " - " + horarios.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), valorspinners, Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), TelaPrincipal.class));
            }
        });
    }
}
