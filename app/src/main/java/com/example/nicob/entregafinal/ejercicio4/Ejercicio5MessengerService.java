package com.example.nicob.entregafinal.ejercicio4;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Random;

public class Ejercicio5MessengerService extends Service {

    private static final String TAG = Ejercicio5MessengerService.class.getCanonicalName();
    private final Random nGenerator = new Random();
    public static final int ME = 3;
    static final int NEXT_RANDOM = 0;//1;
    public  static final String RESPONSE_ACTION = "Respuesta de la iteration";
    public static final String RESPONSE = "Response";

    final Messenger mMessenger = new Messenger(new IncomingHandler());
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    class IncomingHandler extends Handler {//implements com.example.nicob.entregafinal.ejercicio4.Ejercicio5IncomingHandler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEXT_RANDOM:
                    String text = "Random generated" + nGenerator.nextInt(100);
                    Log.d(TAG, text);
                    Intent response = new Intent(Ejercicio5MessengerService.RESPONSE_ACTION);
                    response.putExtra(Ejercicio5MessengerService.RESPONSE_ACTION, text);
                    response.putExtra(Ejercicio4Activity.WHO, ME);
                    LocalBroadcastManager.getInstance(Ejercicio5MessengerService.this).sendBroadcast(response);
                    //stopSelf(msg.arg1);
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }
}
