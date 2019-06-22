package com.cursoandroid.danielmorsch.ibeauty.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Horario;

import java.util.Collection;
import java.util.List;

public class HorariosListAdapter extends RecyclerView.Adapter<HorariosListViewHolder> {

    private List<Horario> dataset;

    private HorariosListViewHolder.AdapterItemClickListener delegate;

    public HorariosListAdapter(Collection<Horario> dataset, HorariosListViewHolder.AdapterItemClickListener delegate) {
        this.dataset = (List<Horario>) dataset;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public HorariosListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_two_row_list, parent, false);
        return new HorariosListViewHolder(v, delegate);
    }

    @Override
    public void onBindViewHolder(@NonNull HorariosListViewHolder holder, int position) {
        holder.bind(position, dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}
