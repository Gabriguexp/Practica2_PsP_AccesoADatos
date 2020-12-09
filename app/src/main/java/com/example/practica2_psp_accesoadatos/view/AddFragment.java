package com.example.practica2_psp_accesoadatos.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practica2_psp_accesoadatos.R;
import com.example.practica2_psp_accesoadatos.model.Repository;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;
import com.example.practica2_psp_accesoadatos.viewmodel.ViewModel;


public class AddFragment extends Fragment {
    EditText nombreEt, telefonoEt, fechaEt;
    Button addBt;
    ViewModel viewModel;
    public AddFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        nombreEt = view.findViewById(R.id.nombreEt);
        telefonoEt = view.findViewById(R.id.telefonoEt);
        fechaEt = view.findViewById(R.id.fechaEt);
        addBt = view.findViewById(R.id.addBt);

        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String nombre = nombreEt.getText().toString();

                int telefono =  Integer.parseInt(telefonoEt.getText().toString());
                String fecha = fechaEt.getText().toString();
                if( nombre.isEmpty() || telefono == 0){
                    Toast.makeText(getContext(), "Debes rellenar los campos con asterisco", Toast.LENGTH_LONG).show();
                } else{
                    viewModel.insert(new Amigos(nombre,telefono,fecha));
                    Toast.makeText(getContext(), "Amigo añadido correctamente", Toast.LENGTH_LONG).show();
                    nombreEt.setText("");
                    telefonoEt.setText("");
                    fechaEt.setText("");
                }
            }catch (Exception ex){

                }
            }
        });
        Button volverBt = view.findViewById(R.id.volverBt);
        volverBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(AddFragment.this).navigate(R.id.action_addFragment_to_listaFragment);
            }
        });

    }
}