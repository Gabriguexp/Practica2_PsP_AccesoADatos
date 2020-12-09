package com.example.practica2_psp_accesoadatos.view;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.practica2_psp_accesoadatos.R;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;
import com.example.practica2_psp_accesoadatos.viewmodel.ViewModel;

import java.util.ArrayList;

public class ImportarFragment extends Fragment {
    Button cancelarBt, importarBt;
    ViewModel viewModel;
    public ImportarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_importar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cancelarBt = view.findViewById(R.id.vueltaalistabt);
        importarBt = view.findViewById(R.id.importarBt);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        cancelarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ImportarFragment.this).navigate(R.id.action_importarFragment_to_listaFragment);
            }
        });


        ArrayList<Amigos> amigos= getContactos();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerImportar);
        ImportarAdapter adapter = new ImportarAdapter(amigos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        importarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Importar
                ArrayList<Amigos> importados =adapter.importar();
                for (Amigos a : importados){
                    viewModel.insert(a);
                }
                NavHostFragment.findNavController(ImportarFragment.this).navigate(R.id.action_importarFragment_to_listaFragment);
            }
        });
    }


    public ArrayList<Amigos> getContactos(){
        ArrayList<Amigos> amigosArrayList= new ArrayList<>();
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String nombre = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            //String fecha = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.))
            Log.v("xyz", "nombre: "+nombre);
            int phone = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
            Log.v("xyz", "has phone number: "+phone);
            if(phone >0){
                Cursor phoneCursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                        new String[]{id},
                        null);
                Log.v("xyz", "ENTRO AQUI");
                while(phoneCursor.moveToNext()){
                    int phoneNum = phoneCursor.getInt(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Log.v("xyz", "Num: "+phoneNum);
                    amigosArrayList.add(new Amigos(nombre, phoneNum , "null"));
                }
                phoneCursor.close();

            }
        }
        cursor.close();
        return amigosArrayList;
    }
}