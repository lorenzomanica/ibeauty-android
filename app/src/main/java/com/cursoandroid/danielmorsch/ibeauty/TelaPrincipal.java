package com.cursoandroid.danielmorsch.ibeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TelaPrincipal extends AppCompatActivity {
    private TextView bemvindo;
    private ListView listaempresas;
    private String[] empreas = {"Sexton", "Estética Belle", "Hugo Beauty", "New Bordeaux", "Blush Salão da Beleza",
                                "Frenzy Saloon", "W Station", "Barber Shop", "Clio Beleza", "Cubo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        bemvindo = findViewById(R.id.texto_bemvindo_id);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            //monta texto de boas vindas
            bemvindo.setText(bemvindo.getText().toString() + " " + extra.getString("usuario"));
        }

        listaempresas = findViewById(R.id.listview_empresas_id);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, empreas);

        listaempresas.setAdapter(adaptador);

        listaempresas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Pega o valor clicado e vai pra pagina de detalhe da empresa
                String valorclicado = listaempresas.getItemAtPosition(i).toString();
                Intent intent = new Intent(getApplicationContext(), DetalheEmpresa.class);
                intent.putExtra("empresa", valorclicado);
                startActivity(intent);
            }
        });
    }
}
