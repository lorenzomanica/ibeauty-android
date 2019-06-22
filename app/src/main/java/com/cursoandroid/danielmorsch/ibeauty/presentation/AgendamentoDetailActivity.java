package com.cursoandroid.danielmorsch.ibeauty.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Agendamento;
import com.cursoandroid.danielmorsch.ibeauty.services.AgendamentoService;
import com.cursoandroid.danielmorsch.ibeauty.services.ConnectionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendamentoDetailActivity extends AppCompatActivity {

    Agendamento agendamento;


    @BindView(R.id.text_service_name)
    TextView text_service_name;
    @BindView(R.id.text_date_time)
    TextView text_date_time;
    @BindView(R.id.text_username)
    TextView text_username;
    @BindView(R.id.text_status)
    TextView text_status;
    @BindView(R.id.button_confirm)
    Button button_confirm;
    @BindView(R.id.button_cancel)
    Button button_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento_detail);

        ButterKnife.bind(this);

        if (getIntent().getExtras().containsKey("AGENDAMENTO")) {
            agendamento = (Agendamento) getIntent().getExtras().get("AGENDAMENTO");
            initValues(agendamento);
        }
    }

    private void initValues(Agendamento agendamento) {
        text_service_name.setText(agendamento.getServico().getNome());
        text_date_time.setText(agendamento.getHorario().getDataHora());
        text_username.setText(agendamento.getPessoa().getNome());
        text_status.setText(String.format("Status: %d", agendamento.getEstado()));
        button_cancel.setEnabled(agendamento.getEstado() == 1);
        button_confirm.setEnabled(agendamento.getEstado() == 1);
    }



    @OnClick(R.id.button_confirm)
    public void onConfirmClick() {
        agendamento.setEstado(2);
        doAgendamentoUpdate(agendamento);
    }



    @OnClick(R.id.button_cancel)
    public void onCancelClick() {
        agendamento.setEstado(0);
        doAgendamentoUpdate(agendamento);
    }


    private void doAgendamentoUpdate(Agendamento agendamento) {
        AgendamentoService service = ConnectionManager.getInstance().createAgendamentoService();
        Call<Void> call = service.confirmAgendamento(agendamento);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    goToHome();
                } else {
                    showMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showMessage("Falha na atualização.");
            }
        });
    }

    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void goToHome() {
        finish();
    }
}
