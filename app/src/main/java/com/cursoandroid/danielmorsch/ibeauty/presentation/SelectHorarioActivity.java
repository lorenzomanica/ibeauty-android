package com.cursoandroid.danielmorsch.ibeauty.presentation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Horario;
import com.cursoandroid.danielmorsch.ibeauty.presentation.adapters.HorariosListAdapter;
import com.cursoandroid.danielmorsch.ibeauty.presentation.adapters.HorariosListViewHolder;
import com.cursoandroid.danielmorsch.ibeauty.services.ConnectionManager;
import com.cursoandroid.danielmorsch.ibeauty.services.HorarioSvcResponse;
import com.cursoandroid.danielmorsch.ibeauty.services.HorariosService;

import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.MutableDateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectHorarioActivity extends AppCompatActivity {

    String filter;
    Long companyId;
    MutableDateTime selectedDate = new MutableDateTime(DateTimeZone.UTC);

    HorariosListAdapter adapter;

    List<Horario> dataset = new ArrayList<>();


    @BindView(R.id.list_services)
    RecyclerView recyclerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_horario);

        ButterKnife.bind(this);

        adapter = new HorariosListAdapter(dataset, delegate);
        recyclerList.setAdapter(adapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));


        if (getIntent().getExtras() != null) {
            Bundle extra = getIntent().getExtras();
            if (extra.containsKey("COMPANY_ID")) {
                this.companyId = extra.getLong("COMPANY_ID");
            }
        }


        showDatePickerDialog();
    }

    private void showDatePickerDialog() {
        DateTime dt = DateTime.now();
        DatePickerDialog dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        selectedDate.setDate(datePicker.getYear(), datePicker.getMonth()+1, datePicker.getDayOfMonth());
                        showTimePickerDialog();
                    }
                }, dt.getYear(), dt.getMonthOfYear()-1, dt.getDayOfMonth());
        dialog.show();
    }

    private void showTimePickerDialog() {
        DateTime dt = DateTime.now();

        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                selectedDate.setTime(timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0, 0);
                listDateTimeOptionsFiltered(selectedDate.toDateTime());
            }
        }, dt.getHourOfDay(), dt.getMinuteOfHour(), true);
        dialog.show();
    }


    private HorariosListViewHolder.AdapterItemClickListener delegate =
            position -> {
                Intent extra = new Intent();
                extra.putExtra("RESULT", dataset.get(position));
                setResult(101, extra);
                finish();
            };


    private void listDateTimeOptionsFiltered(DateTime filter) {
        HorariosService service = ConnectionManager.getInstance().createHorariosService();
        Call<HorarioSvcResponse> call = service.getHorariosFromEmpresaWithTime(companyId, TimeUnit.MILLISECONDS.toSeconds(selectedDate.getMillis()), 0);
        call.enqueue(new Callback<HorarioSvcResponse>() {
            @Override
            public void onResponse(Call<HorarioSvcResponse> call, Response<HorarioSvcResponse> response) {
                if (response.isSuccessful()) {
                    HorarioSvcResponse data = response.body();

                    for (Horario it : data.content) {
                        dataset.add(it);
                    }

                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<HorarioSvcResponse> call, Throwable t) {
                dispatchError(t.getMessage());
            }
        });

    }

    private void dispatchError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
