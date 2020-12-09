package com.example.practica2_psp_accesoadatos.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="llamadas")
public class Llamadas {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
   @ColumnInfo(name = "idamigo")
    private long idAmigo;

    @NonNull
    @ColumnInfo(name = "fechallamada")
    private String fechaLlamada;

    public Llamadas(long idAmigo, @NonNull String fechaLlamada) {
        this.idAmigo = idAmigo;
        this.fechaLlamada = fechaLlamada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(long idAmigo) {
        this.idAmigo = idAmigo;
    }

    @NonNull
    public String getFechaLlamada() {
        return fechaLlamada;
    }

    public void setFechaLlamada(@NonNull String fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }

    @Override
    public String toString() {
        return "Llamadas{" +
                "id=" + id +
                ", idAmigo=" + idAmigo +
                ", fechaLlamada='" + fechaLlamada + '\'' +
                '}';
    }
}
