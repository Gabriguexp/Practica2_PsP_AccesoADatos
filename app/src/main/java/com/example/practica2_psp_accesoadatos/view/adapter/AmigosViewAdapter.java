package com.example.practica2_psp_accesoadatos.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica2_psp_accesoadatos.R;
import com.example.practica2_psp_accesoadatos.model.Repository;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;
import com.example.practica2_psp_accesoadatos.view.AmigoActivity;

import java.util.concurrent.ExecutionException;

public class AmigosViewAdapter extends ListAdapter<Amigos, AmigosViewAdapter.AmigosViewHolder> {

    private Context context;
    Repository repository;
    public AmigosViewAdapter(@NonNull DiffUtil.ItemCallback<Amigos> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
        repository = new Repository(context);

    }

    @NonNull
    @Override
    public AmigosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AmigosViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AmigosViewHolder holder, int position) {
        Amigos actual = getItem(position);
        try {
            holder.datosTv.setText(actual.toString() + ": "+repository.getLlamadas(actual.getId())+" llamadas");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AmigoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("amigo", actual);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }




    public static class AmigosViewHolder extends RecyclerView.ViewHolder{
        private TextView datosTv;
        ConstraintLayout parentLayout;


        public AmigosViewHolder(@NonNull View itemView) {
            super(itemView);
         datosTv = itemView.findViewById(R.id.datosTv);
         parentLayout = itemView.findViewById(R.id.recyclerParentLayout);

        }
        static AmigosViewHolder create(ViewGroup parent){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recycler, parent, false);
            return new AmigosViewHolder(view);
        }
    }

    public static class AgendaDiff extends DiffUtil.ItemCallback<Amigos> {

        @Override
        public boolean areItemsTheSame(@NonNull Amigos oldItem, @NonNull Amigos newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Amigos oldItem, @NonNull Amigos newItem) {
            return oldItem.getNombre().equalsIgnoreCase(newItem.getNombre());
        }
    }
}
