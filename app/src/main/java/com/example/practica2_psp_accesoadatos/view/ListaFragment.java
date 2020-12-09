package com.example.practica2_psp_accesoadatos.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.practica2_psp_accesoadatos.MainActivity;
import com.example.practica2_psp_accesoadatos.R;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;
import com.example.practica2_psp_accesoadatos.view.adapter.AmigosViewAdapter;
import com.example.practica2_psp_accesoadatos.viewmodel.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaFragment extends Fragment {
    private FloatingActionButton fabAdd;
    private ViewModel viewModel;
    public ListaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ViewModel.class);
        fabAdd = view.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ListaFragment.this)
                        .navigate(R.id.action_listaFragment_to_addFragment);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerlista);
        final AmigosViewAdapter adapter = new AmigosViewAdapter(new AmigosViewAdapter.AgendaDiff(), getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        viewModel.getLiveListAmigos().observe(getViewLifecycleOwner(), new Observer<List<Amigos>>() {
            @Override
            public void onChanged(List<Amigos> amigos) {
                adapter.submitList(amigos);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case(R.id.importarmn):
                int contactPermission = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS);
                if (contactPermission == PackageManager.PERMISSION_GRANTED){
                    NavHostFragment.findNavController(ListaFragment.this).navigate(R.id.action_listaFragment_to_importarFragment);
                } else{
                    requestPermissions( new String[]{Manifest.permission.READ_CONTACTS}, MainActivity.CONTACT_PERMISSION);
                }

        }
        return super.onOptionsItemSelected(item);
    }
}