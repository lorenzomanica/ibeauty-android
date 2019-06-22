package com.cursoandroid.danielmorsch.ibeauty.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Agendamento;
import com.cursoandroid.danielmorsch.ibeauty.domain.DadosPagamento;
import com.cursoandroid.danielmorsch.ibeauty.domain.Horario;
import com.cursoandroid.danielmorsch.ibeauty.domain.Pessoa;
import com.cursoandroid.danielmorsch.ibeauty.domain.Servico;
import com.cursoandroid.danielmorsch.ibeauty.services.AgendamentoService;
import com.cursoandroid.danielmorsch.ibeauty.services.AgendamentoSvcResponse;
import com.cursoandroid.danielmorsch.ibeauty.services.ConnectionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicoDetailActivity extends AppCompatActivity {


    @BindView(R.id.text_service_name)
    TextView serviceName;
    @BindView(R.id.text_service_price)
    TextView servicePrice;
    @BindView(R.id.text_company_name)
    TextView companyName;
    @BindView(R.id.text_company_address)
    TextView companyAddress;
    @BindView(R.id.text_company_phone)
    TextView companyPhone;
    @BindView(R.id.button_select_time)
    TextView buttonTime;
    @BindView(R.id.button_save)
    TextView buttonSave;

    Servico servico;
    Horario horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_servico);

        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            Bundle extra = getIntent().getExtras();
            if (extra.containsKey("ITEM")) {
                this.servico = (Servico) extra.getSerializable("ITEM");
            }
        }

        initValues(servico);
    }

    private void initValues(Servico item) {
        serviceName.setText(item != null ? item.getNome() : "Not set");
        servicePrice.setText(item != null ? item.getPreco() : "Not set");
        companyName.setText(item != null ? item.getEmpresa().getNome() : "Not set");
        companyAddress.setText(item != null
                ? item.getEmpresa().getEndereco() + "\n"
                + item.getEmpresa().getCidade() : "Not set");
        companyPhone.setText(item != null ? item.getEmpresa().getTelefone() : "Not set");
    }


    @OnClick(R.id.button_select_time)
    public void onSelectTimeClick() {
        showDates();
    }

    private void showDates() {
        Intent i = new Intent(this, SelectHorarioActivity.class);
        i.putExtra("COMPANY_ID", servico.getEmpresa().getCodEmpresa());
        startActivityForResult(i, 100);
    }

    @OnClick(R.id.button_save)
    public void onSaveClick() {
        saveAgendamento();
    }

    private void saveAgendamento() {

        Pessoa p = new Pessoa();
        p.setCodPessoa(50);

        DadosPagamento dp = new DadosPagamento();
        dp.setCodDadoPagto(50);

        Agendamento a = new Agendamento();
        a.setPessoa(p);
        a.setDadosPagamento(dp);
        a.setCodEmpresa(servico.getEmpresa().getCodEmpresa());
        a.setServico(servico);
        a.setCodFuncionario(horario.getFuncionario().getCodFuncionario());
        a.setHorario(horario);
        a.setEstado(1);

        AgendamentoService service = ConnectionManager.getInstance().createAgendamentoService();
        Call<Void> call = service.saveAgendamento(a);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ServicoDetailActivity.this, "Sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ServicoDetailActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ServicoDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == 101) {

            if (data.getExtras().containsKey("RESULT")) {
                horario = (Horario) data.getExtras().get("RESULT");
                buttonTime.setText(horario.getDataHora());
            }
        }
    }
}
