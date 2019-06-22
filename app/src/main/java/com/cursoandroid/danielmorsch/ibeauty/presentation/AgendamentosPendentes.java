package com.cursoandroid.danielmorsch.ibeauty.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;

import java.util.ArrayList;


public class AgendamentosPendentes extends Activity {
    private ArrayList<String> clientes = new ArrayList<String>();
    private ArrayList<String> horarios = new ArrayList<String>();
    private ArrayList<CheckBox> checkboxes = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clientes.add("Caroline");
        clientes.add("Daniel");
        clientes.add("Lorenzo");
        clientes.add("Fulano");
        clientes.add("Beltrano");

        horarios.add("08:00");
        horarios.add("11:00");
        horarios.add("16:00");
        horarios.add("09:00");
        horarios.add("14:00");
        //cria o scrollview e o linearLayout
        ScrollView sv = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        //texto do topo
        TextView tv = new TextView(this);
        tv.setText("Agendamentos pendentes");
        ll.addView(tv);
        //cria checkboxes e coloca elas na view de acordo com a quantidade de clientes
        for(int i = 0; i < clientes.size(); i++) {
            CheckBox cb = new CheckBox(this);
            cb.setText(clientes.get(i) + " " + horarios.get(i));
            ll.addView(cb);
            checkboxes.add(cb);
        }
        //botao de confirmar agendamento
        Button b = new Button(this);
        b.setText("Confirmar agendamentos");
        ll.addView(b);
        //itera sobre todos os checkboxes marcados e remove eles
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < clientes.size(); i++){
                    if(checkboxes.get(i).isChecked()){
                        checkboxes.remove(i);
                        clientes.remove(i);
                        horarios.remove(i);
                        startActivity(new Intent(getApplicationContext(), TelaPrincipalEmpresa.class));
                    }
                }
            }
        });
        this.setContentView(sv);
    }
}
