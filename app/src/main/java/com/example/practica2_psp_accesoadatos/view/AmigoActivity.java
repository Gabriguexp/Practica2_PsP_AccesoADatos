package com.example.practica2_psp_accesoadatos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.practica2_psp_accesoadatos.R;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;
import com.example.practica2_psp_accesoadatos.viewmodel.ViewModel;

import java.util.List;

public class AmigoActivity extends AppCompatActivity {
    TextView nombreTv, telefonoTv, fechaTv;
    Button editarBt, borrarBt;
    ViewModel viewModel;
    Amigos vistaAmigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigo);
        vistaAmigo = (Amigos) getIntent().getSerializableExtra("amigo");
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        nombreTv = findViewById(R.id.nombretv);
        telefonoTv = findViewById(R.id.telefonotv);
        fechaTv = findViewById(R.id.fechatv);
        editarBt = findViewById(R.id.editarbt);
        borrarBt = findViewById(R.id.borrarbt);

        editarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmigoActivity.this,EditarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("amigo", vistaAmigo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        borrarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.delete(vistaAmigo);
                finish();
            }
        });

        viewModel.getLiveListAmigos().observeForever(new Observer<List<Amigos>>() {
            @Override
            public void onChanged(List<Amigos> amigos) {
                for(Amigos a : amigos){
                    if(a.getId() == vistaAmigo.getId()){
                        vistaAmigo = a;
                    }
                }
                nombreTv.setText("Nombre: "+ vistaAmigo.getNombre());
                telefonoTv.setText("Telefono: "+ vistaAmigo.getTelefono());
                fechaTv.setText("Fecha Nacimiento: "+ vistaAmigo.getFechaNac());
            }
        });
    }
}