package com.cursoandroid.danielmorsch.ibeauty.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Servico;

import java.util.Collection;
import java.util.List;

public class ServicosListAdapter extends RecyclerView.Adapter<ServicosListViewHolder> {

    private List<Servico> dataset;

    private ServicosListViewHolder.AdapterItemClickListener delegate;

    public ServicosListAdapter(Collection<Servico> dataset, ServicosListViewHolder.AdapterItemClickListener delegate) {
        this.dataset = (List<Servico>) dataset;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public ServicosListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_two_row_list, parent, false);
        return new ServicosListViewHolder(v, delegate);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicosListViewHolder holder, int position) {
        holder.bind(position, dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}
