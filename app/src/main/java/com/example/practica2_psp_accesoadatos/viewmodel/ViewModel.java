package com.example.practica2_psp_accesoadatos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.practica2_psp_accesoadatos.model.Repository;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;
import com.example.practica2_psp_accesoadatos.model.pojo.Llamadas;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Amigos>> liveData;
    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        liveData = repository.getLiveListAmigos();
    }

    public LiveData<List<Llamadas>> getLiveListLlamadas() {
        return repository.getLiveListLlamadas();
    }

    public LiveData<List<Amigos>> getLiveListAmigos() {
        return repository.getLiveListAmigos();
    }

    public void insert(Llamadas llamadas) {
        repository.insert(llamadas);
    }

    public void insert(Amigos amigo) {
        repository.insert(amigo);
    }

    public Amigos getAmigo(int telf) throws ExecutionException, InterruptedException {
        return repository.getAmigo(telf);
    }

    public int getId(int num) throws ExecutionException, InterruptedException {
        return repository.getId(num);
    }

    public void delete(Amigos amigo) {
        repository.delete(amigo);
    }

    public void update(Amigos amigo) {
        repository.update(amigo);
    }
}
