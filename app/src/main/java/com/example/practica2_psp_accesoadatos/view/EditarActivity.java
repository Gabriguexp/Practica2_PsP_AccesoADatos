package com.example.practica2_psp_accesoadatos.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.practica2_psp_accesoadatos.viewmodel.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.practica2_psp_accesoadatos.R;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;

public class EditarActivity extends AppCompatActivity {
    EditText editarNombreEt, editarTelefonoEt, editarFechaEt;
    Button cancelarBt, guardarBt;
    ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        init();


    }

    private void init() {
        Amigos amigo = (Amigos) getIntent().getSerializableExtra("amigo");
        editarNombreEt = findViewById(R.id.editarNombreEt);
        editarTelefonoEt = findViewById(R.id.editarTelefonoEt);
        editarFechaEt = findViewById(R.id.editarFechaEt);
        cancelarBt = findViewById(R.id.cancelarBt);
        guardarBt = findViewById(R.id.guardarBt);
        editarNombreEt.setText(amigo.getNombre());
        editarTelefonoEt.setText(""+amigo.getTelefono());
        editarFechaEt.setText(amigo.getFechaNac());

        cancelarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guardarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amigo.setNombre(editarNombreEt.getText().toString());
                amigo.setTelefono(Integer.parseInt(editarTelefonoEt.getText().toString()));
                amigo.setFechaNac(editarFechaEt.getText().toString());
                viewModel.update(amigo);
                finish();

            }
        });
    }
}