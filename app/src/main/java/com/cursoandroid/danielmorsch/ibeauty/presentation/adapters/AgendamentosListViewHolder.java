package com.cursoandroid.danielmorsch.ibeauty.presentation.adapters;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Agendamento;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgendamentosListViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.text_service_name)
    TextView serviceName;

    @BindView(R.id.text_company_name)
    TextView companyName;

    @BindView(R.id.text_service_price)
    TextView servicePrice;

    private int position;
    private final AdapterItemClickListener itemClicListener;


    public AgendamentosListViewHolder(@NonNull View itemView, AdapterItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemClicListener = listener;
    }

    public void bind(int position, Agendamento item) {
        this.position = position;
        serviceName.setText(item.getServico().getNome());
        companyName.setText(item.getHorario().getDataHora());
        servicePrice.setText(String.format("%d", item.getEstado()));
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
