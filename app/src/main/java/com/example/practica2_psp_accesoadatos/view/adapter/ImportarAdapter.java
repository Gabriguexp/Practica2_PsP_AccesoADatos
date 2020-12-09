package com.example.practica2_psp_accesoadatos.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica2_psp_accesoadatos.R;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;

import java.util.ArrayList;

public class ImportarAdapter extends RecyclerView.Adapter<ImportarAdapter.ViewHolder> {

    ArrayList<Amigos> amigosArrayList;
    static ArrayList<CheckBox> checkBoxes;

    public ImportarAdapter(ArrayList<Amigos> amigosArrayList){
        this.amigosArrayList = amigosArrayList;
        checkBoxes = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_importar, parent, false);
        ViewHolder viewHolder = new ViewHolder(vista);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cb.setText(amigosArrayList.get(position).toString());
        checkBoxes.add(holder.cb);
    }

    public ArrayList<Amigos> importar(){
        ArrayList<Amigos> contactos = new ArrayList<>();
        for(CheckBox c : checkBoxes){
            if(c.isChecked()){
                String texto = c.getText().toString();
                String[] cad = texto.split(" - ");
                String nombre = cad[0];
                int telefono = Integer.parseInt(cad[1]);
                String fecha = cad[2];
                contactos.add(new Amigos(nombre, telefono, fecha));
            }
        }
        return contactos;
    }



    @Override
    public int getItemCount() {
        return amigosArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.checkBox);
        }
    }
}
