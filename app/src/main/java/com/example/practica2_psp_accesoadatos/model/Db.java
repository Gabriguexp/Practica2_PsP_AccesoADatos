package com.example.practica2_psp_accesoadatos.model;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.practica2_psp_accesoadatos.model.dao.AmigosDao;
import com.example.practica2_psp_accesoadatos.model.dao.LlamadasDao;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;
import com.example.practica2_psp_accesoadatos.model.pojo.Llamadas;

@Database(entities = {Amigos.class, Llamadas.class}, version = 1, exportSchema = false)
public abstract class Db extends RoomDatabase {

    public abstract AmigosDao getAmigosDao();
    public abstract LlamadasDao getLlamadasDao();

    private volatile static Db INSTANCE;

    static synchronized Db getDb(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Db.class, "dbamigos").build();
        }
    return INSTANCE;
    }

}
