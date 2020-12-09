package com.example.practica2_psp_accesoadatos.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.practica2_psp_accesoadatos.model.dao.AmigosDao;
import com.example.practica2_psp_accesoadatos.model.dao.LlamadasDao;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;
import com.example.practica2_psp_accesoadatos.model.pojo.Llamadas;
import com.example.practica2_psp_accesoadatos.model.room.Db;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Repository {
    private AmigosDao amigosDao;
    private LlamadasDao llamadasDao;
    private LiveData<List<Llamadas>> liveListLlamadas;
    private LiveData<List<Amigos>> liveListAmigos;

    public Repository(Context context){
        Db db= Db.getDb(context);
        amigosDao = db.getAmigosDao();
        llamadasDao = db.getLlamadasDao();
        liveListAmigos= amigosDao.getAll();
        liveListLlamadas = llamadasDao.getAll();
    }

    public LiveData<List<Llamadas>> getLiveListLlamadas() {
        return liveListLlamadas;
    }

    public LiveData<List<Amigos>> getLiveListAmigos() {
        return liveListAmigos;
    }

    public void insert(Llamadas llamadas){
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                llamadasDao.insert(llamadas);
            }
        });
    }
    public void insert(Amigos amigo){
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                amigosDao.insert(amigo);
            }
        });
    }

    public Amigos getAmigo(int telf) throws ExecutionException, InterruptedException {

        Callable<Amigos> callable = new Callable<Amigos>(){

            @Override
            public Amigos call() throws Exception {
                return amigosDao.getAmigo(telf);
            }
        };
        Future<Amigos> future = UtilThread.threadExecutorPool.submit(callable);

        return future.get();



    }

    public int getId(int num) throws ExecutionException, InterruptedException {


        Callable<Integer> callable = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return amigosDao.getId(num);
            }
        };
        Future<Integer> future = UtilThread.threadExecutorPool.submit(callable);

        return future.get();
    }
    public void delete(Amigos amigo){
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                amigosDao.delete(amigo);
            }
        });
    }

    public void update(Amigos amigo){
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                amigosDao.update(amigo);
            }
        });
    }

    public int getLlamadas(long id) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return llamadasDao.getNLlamadas(id);
            }
        };
        Future<Integer> future = UtilThread.threadExecutorPool.submit(callable);

        return future.get();
    }

}
