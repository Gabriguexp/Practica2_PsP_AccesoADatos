package com.example.practica2_psp_accesoadatos.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="amigos")
public class Amigos implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;


    @ColumnInfo(name = "fechanac")
    private String fechaNac;

    @NonNull
    @ColumnInfo(name = "telefono")
    private int telefono;

    public Amigos(String nombre, int telefono, String fechaNac) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre+" - " + telefono+" - " + fechaNac;
    }
}
