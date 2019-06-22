package com.cursoandroid.danielmorsch.ibeauty.presentation.adapters;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Horario;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HorariosListViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.text_service_name)
    TextView serviceName;

    @BindView(R.id.text_company_name)
    TextView companyName;

    @BindView(R.id.text_service_price)
    TextView servicePrice;

    private int position;
    private final AdapterItemClickListener itemClicListener;


    public HorariosListViewHolder(@NonNull View itemView, AdapterItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemClicListener = listener;
    }

    public void bind(int position, Horario horario) {
        this.position = position;
        serviceName.setText(horario.getDataHora());
        servicePrice.setText(horario.getFuncionario().getUsuario().getUsuario());
        companyName.setText(horario.getFuncionario().getPapel());
    }

    @OnClick(R.id.item)
    public void onItemClick() {
        if (itemClicListener != null)
            itemClicListener.onClick(position);
    }

    public interface AdapterItemClickListener {
        void onClick(int position);
    }
}
