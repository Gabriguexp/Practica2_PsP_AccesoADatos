package com.example.practica2_psp_accesoadatos.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;

import java.util.List;

@Dao
public interface AmigosDao {
    @Delete
    int delete (Amigos amigos);

    @Insert
    long insert(Amigos amigos);

    @Update
    int update(Amigos amigos);

    @Query("select * from amigos order by nombre")
    LiveData<List<Amigos>> getAll();

    @Query("select * from amigos where telefono = :telf")
    Amigos getAmigo(int telf);

    @Query("select id from amigos where telefono = :num ")
    int getId(int num);

}
