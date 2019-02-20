package com.example.nicob.entregafinal.ejercicio4;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class Ejercicio4MyIntentService extends IntentService {

    public static final String TAG = Ejercicio4MyIntentService.class.getCanonicalName();

    public  static final String RESPONSE_ACTION = "Respuesta de la iteration";
    public static final String RESPONSE = "Response";

    public static final int ME = 1;

    public Ejercicio4MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int iteration = intent.getIntExtra(Ejercicio4Activity.ITERATION,-1);
            String msg = "(IS) Processing iteration "+iteration;
            Intent response = new Intent(RESPONSE_ACTION);
            response.putExtra(RESPONSE,msg);
            response.putExtra(Ejercicio4Activity.WHO,ME);
            Log.d(TAG,msg);
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            LocalBroadcastManager.getInstance(this).sendBroadcast(response);
        }
    }
}