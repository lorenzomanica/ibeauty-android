package com.cursoandroid.danielmorsch.ibeauty.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Agendamento;

import java.util.Collection;
import java.util.List;

public class AgendamentosListAdapter extends RecyclerView.Adapter<AgendamentosListViewHolder> {

    private List<Agendamento> dataset;

    private AgendamentosListViewHolder.AdapterItemClickListener delegate;

    public AgendamentosListAdapter(Collection<Agendamento> dataset, AgendamentosListViewHolder.AdapterItemClickListener delegate) {
        this.dataset = (List<Agendamento>) dataset;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public AgendamentosListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_two_row_list, parent, false);
        return new AgendamentosListViewHolder(v, delegate);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendamentosListViewHolder holder, int position) {
        holder.bind(position, dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}

