package com.example.practica2_psp_accesoadatos.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;
import com.example.practica2_psp_accesoadatos.model.pojo.Llamadas;

import java.util.List;

@Dao
public interface LlamadasDao {
    @Delete
    int delete (Llamadas llamada);

    @Insert
    long insert(Llamadas llamada);

    @Update
    int update(Llamadas llamada);

    @Query("select * from llamadas order by idamigo")
    LiveData<List<Llamadas>> getAll();

    @Query("select count() from llamadas where idamigo = :idAmigo")
    int getNLlamadas(long idAmigo);

}
