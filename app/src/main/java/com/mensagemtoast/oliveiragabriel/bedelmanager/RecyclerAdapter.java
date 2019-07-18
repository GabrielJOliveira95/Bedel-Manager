package com.mensagemtoast.oliveiragabriel.bedelmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<RecyclerList> list;

    public RecyclerAdapter(List<RecyclerList> list){

        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemrecyclerview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder recyclerList, int i) {
           RecyclerList rl = list.get(i);
           recyclerList.sala_id.setText("Sala " + rl.sala);
           recyclerList.equipamento_id.setText(rl.equipamento);
           recyclerList.datanumber_id.setText("Data N° " + rl.numeroDoData);
           recyclerList.horario_id.setText(rl.horario);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
