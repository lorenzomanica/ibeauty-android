package com.cursoandroid.danielmorsch.ibeauty.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Agendamento;
import com.cursoandroid.danielmorsch.ibeauty.domain.Funcionario;
import com.cursoandroid.danielmorsch.ibeauty.domain.Servico;
import com.cursoandroid.danielmorsch.ibeauty.domain.Usuario;
import com.cursoandroid.danielmorsch.ibeauty.presentation.adapters.AgendamentosListAdapter;
import com.cursoandroid.danielmorsch.ibeauty.presentation.adapters.AgendamentosListViewHolder;
import com.cursoandroid.danielmorsch.ibeauty.presentation.adapters.ServicosListAdapter;
import com.cursoandroid.danielmorsch.ibeauty.presentation.adapters.ServicosListViewHolder;
import com.cursoandroid.danielmorsch.ibeauty.services.AgendamentoService;
import com.cursoandroid.danielmorsch.ibeauty.services.AgendamentoSvcResponse;
import com.cursoandroid.danielmorsch.ibeauty.services.ConnectionManager;
import com.cursoandroid.danielmorsch.ibeauty.services.FuncionarioService;
import com.cursoandroid.danielmorsch.ibeauty.services.ServicosService;
import com.cursoandroid.danielmorsch.ibeauty.services.ServicosSvcResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    String filter;

    ServicosListAdapter servicesAdapter;
    AgendamentosListAdapter reservationsAdapter;

    Usuario user = new Usuario();
    Funcionario funcionario;
    boolean agendamentoClickable;

    List<Servico> serviceDataSet = new ArrayList<>();
    List<Agendamento> reservationDataSet = new ArrayList<>();

    @BindView(R.id.text_welcome)
    TextView textWelcome;

    @BindView(R.id.list_services)
    RecyclerView recyclerListServices;

    @BindView(R.id.list_agendamentos)
    RecyclerView recyclerListReservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        if (getIntent().getExtras().containsKey("USER")) {
            this.user = (Usuario) getIntent().getExtras().get("USER");
            textWelcome.setText(String.format("Bem-vindo, %s", user.getUsuario()));
            findEmployeeInfo(user.getCodUsuario());
        }

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

        servicesAdapter = new ServicosListAdapter(serviceDataSet, servicesDelegate);
        recyclerListServices.setAdapter(servicesAdapter);
        recyclerListServices.setLayoutManager(layoutManager1);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        reservationsAdapter = new AgendamentosListAdapter(reservationDataSet, reservationDelegate);
        recyclerListReservations.setAdapter(reservationsAdapter);
        recyclerListReservations.setLayoutManager(layoutManager2);

        doRefresh();
    }

    private void findEmployeeInfo(long codUsuario) {
        FuncionarioService service = ConnectionManager.getInstance().createFuncionarioService();
        Call<Funcionario> call = service.getFuncionarioInfoByUserId(codUsuario);
        call.enqueue(new Callback<Funcionario>() {
            @Override
            public void onResponse(Call<Funcionario> call, Response<Funcionario> response) {
                if (response.isSuccessful()) {
                    setFuncionarioOptions(response.body());
                }
            }

            @Override
            public void onFailure(Call<Funcionario> call, Throwable t) {

            }
        });
    }

    private void setFuncionarioOptions(Funcionario info) {
        if (info != null) {
            funcionario = info;
            agendamentoClickable = true;
            recyclerListServices.setVisibility(View.INVISIBLE);
        }
    }

    private void doRefresh() {
        findServicesFiltered(filter);
        getReservationsFromId(50L, 0);
    }

    private ServicosListViewHolder.AdapterItemClickListener servicesDelegate =
            position -> {
                Intent i = new Intent(this, ServicoDetailActivity.class);
                i.putExtra("ID", position);
                i.putExtra("ITEM", serviceDataSet.get(position));
                startActivity(i);
            };

    private AgendamentosListViewHolder.AdapterItemClickListener reservationDelegate =
            position -> {
                if (agendamentoClickable) {
                    Intent i = new Intent(this, AgendamentoDetailActivity.class);
                    i.putExtra("AGENDAMENTO", reservationDataSet.get(position));
                    startActivity(i);
                }
            };

    private void findServicesFiltered(String filter) {
        ServicosService service = ConnectionManager.getInstance().createServicosService();
        Call<ServicosSvcResponse> call = service.getServicosPaginated(0);
        call.enqueue(new Callback<ServicosSvcResponse>() {
            @Override
            public void onResponse(Call<ServicosSvcResponse> call, Response<ServicosSvcResponse> response) {
                if (response.isSuccessful()) {
                    ServicosSvcResponse data = response.body();
                    serviceDataSet.clear();
                    for (Servico s : data.content) {
                        if (filter == null || filter.isEmpty() || filter.contains(s.getNome())) {
                            serviceDataSet.add(s);
                        }
                    }

                    servicesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ServicosSvcResponse> call, Throwable t) {
                dispatchError(t.getMessage());
            }
        });

    }

    private void getReservationsFromId(Long id, Integer page) {

        AgendamentoService service = ConnectionManager.getInstance().createAgendamentoService();
        Call<AgendamentoSvcResponse> call = service.getAgendamentosFromUserId(id, page);
        call.enqueue(new Callback<AgendamentoSvcResponse>() {
            @Override
            public void onResponse(Call<AgendamentoSvcResponse> call, Response<AgendamentoSvcResponse> response) {
                if (response.isSuccessful()) {
                    AgendamentoSvcResponse data = response.body();
                    reservationDataSet.clear();
                    for (Agendamento s : data.content) {
                        reservationDataSet.add(s);
                    }

                    reservationsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<AgendamentoSvcResponse> call, Throwable t) {
                dispatchError(t.getMessage());
            }
        });

    }

    private void dispatchError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                doRefresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
