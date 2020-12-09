package com.example.practica2_psp_accesoadatos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;

import java.security.Permission;
import java.util.ArrayList;

/*Vamos a implementar la aplicación Amigos.

Importación: La aplicación debe tener una opción de importación de contactos, de tal modo, que nos
mostrará una lista (RecyclerView) con todos los contactos que tengan teléfono.
En esta lista debo poder seleccionar los contactos que quiero importar. Los contactos importados se
guardarán en la tabla Amigos de Room, con la siguiente información: Id, Nombre, Fecha de nacimiento (nullable), Teléfono.
Si un contacto tiene más de un teléfono, deberemos elegir el que queremos guardar.

Funcionamiento normal: Al abrir la aplicación se muestra la lista (RecyclerView) de amigos (Room),
con la siguiente información: nombre, teléfono, fecha nacimiento y número de llamadas recibidas;
a través de esta lista yo podré insertar, editar y borrar a mis amigos de la lista.

La aplicación tendrá además una segunda tabla, que es la tabla Llamadas, con la siguiente información:
Id, IdAmigo, FechaLlamada. Esta tabla se irá poblando desde un BroadcastReceiver de forma automática cada vez que se recibe una llamada.

Todas las operaciones sobre la base de datos y de recuperación de datos desde la lista de contactos se deben ejecutar en hebras.
 */
public class MainActivity extends AppCompatActivity {


    public static final int FUNDAMENTAL_PERMISSIONS = 1;
    public static final int CONTACT_PERMISSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        askPermissions();

    }

    private void askPermissions() {
        //READ_CONTACTS
        //READ_CALL_LOG
        // READ_PHONE_STATE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<>();
            int phoneStatePermission = ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE);
            int callPermissiosn = ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CALL_LOG);

            if(phoneStatePermission != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if(callPermissiosn != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_CALL_LOG);
            }

            if(permissions.size() >0){

                if(shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE) ||
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CALL_LOG)){
                    explainReason(FUNDAMENTAL_PERMISSIONS);
                } else{
                    requestPermissions(permissions.toArray(new String[permissions.size()]), FUNDAMENTAL_PERMISSIONS);
                }
            }
        }

    }

    private void explainReason(int code) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (code){

            case FUNDAMENTAL_PERMISSIONS:

                builder.setTitle("Permisos fundamentales");
                builder.setMessage("Estos permisos son fundamentales para que la aplicacion pueda funcionar");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_PHONE_STATE}, FUNDAMENTAL_PERMISSIONS);
                    }
                });
                builder.setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();

                break;

            case CONTACT_PERMISSION:

                builder.setTitle("Permiso Contactos");
                builder.setMessage("Para importar los contactos es necesario tener permiso para acceder a ellos");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_PERMISSION);
                    }
                });
                builder.setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();

                break;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case FUNDAMENTAL_PERMISSIONS:
                for(int result: grantResults){
                    if(result != PackageManager.PERMISSION_GRANTED){
                        askPermissions();
                        return;
                    }
                }
                break;
            case CONTACT_PERMISSION:
                for(int result: grantResults){
                    if(result == PackageManager.PERMISSION_GRANTED){
                        // Metodo importar contactos
                        return;

                    }
                }
        }
    }
}