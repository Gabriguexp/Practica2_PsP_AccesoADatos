        package com.example.practica2_psp_accesoadatos;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.practica2_psp_accesoadatos.model.Repository;
import com.example.practica2_psp_accesoadatos.model.pojo.Amigos;
import com.example.practica2_psp_accesoadatos.model.pojo.Llamadas;
import com.example.practica2_psp_accesoadatos.viewmodel.ViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

        public class CallReceiver extends BroadcastReceiver {
    Repository repository;
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){

            repository = new Repository(context);
            LiveData<List<Amigos>> data = repository.getLiveListAmigos();
            TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            telephony.listen(new PhoneStateListener(){
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onCallStateChanged(int state, String phoneNumber) {
                    try {
                        super.onCallStateChanged(state, phoneNumber);
                        int num = Integer.parseInt(phoneNumber);


                        Amigos amigo = repository.getAmigo(num);


                        if (amigo!=null){
                            Llamadas llamada = new Llamadas(repository.getId(num) , LocalDate.now().toString());

                            repository.insert(llamada);
                            Log.v("xyz", "llamada insertada");
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            },PhoneStateListener.LISTEN_CALL_STATE);
        }


    }
}
