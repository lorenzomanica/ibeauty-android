package com.cursoandroid.danielmorsch.ibeauty.presentation.adapters;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Servico;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicosListViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.text_service_name)
    TextView serviceName;

    @BindView(R.id.text_company_name)
    TextView companyName;

    @BindView(R.id.text_service_price)
    TextView servicePrice;

    private int position;
    private final AdapterItemClickListener itemClicListener;


    public ServicosListViewHolder(@NonNull View itemView, AdapterItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemClicListener = listener;
    }

    public void bind(int position, Servico servico) {
        this.position = position;
        serviceName.setText(servico.getNome());
        companyName.setText(servico.getEmpresa().getNome());
        servicePrice.setText(servico.getPreco());
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
