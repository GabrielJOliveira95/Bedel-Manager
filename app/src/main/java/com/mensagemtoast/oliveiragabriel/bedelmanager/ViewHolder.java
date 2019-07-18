package com.mensagemtoast.oliveiragabriel.bedelmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {


    public ImageView logo;
    public TextView sala_id;
    public TextView equipamento_id;
    public TextView datanumber_id;
    public TextView horario_id;
    public AppCompatButton btn_deletar_tarefa;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        //logo = itemView.findViewById(R.id.logo_id);
        sala_id = itemView.findViewById(R.id.sala_id);
        equipamento_id = itemView.findViewById(R.id.equipamento_id);
        datanumber_id = itemView.findViewById(R.id.datanumber_id);
        horario_id = itemView.findViewById(R.id.horario_id);
        btn_deletar_tarefa = itemView.findViewById(R.id.btn_deletar_tarefa);

    }
}
