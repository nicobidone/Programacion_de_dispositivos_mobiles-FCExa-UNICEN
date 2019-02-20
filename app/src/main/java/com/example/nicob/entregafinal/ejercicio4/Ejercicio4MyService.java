package com.example.nicob.entregafinal.ejercicio4;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class Ejercicio4MyService extends Service {

    public  static final String RESPONSE_ACTION = "Respuesta de la iteration";
    public static final String RESPONSE = "Response";

    public static final int ME = 2;

    private static String TAG = Ejercicio4MyService.class.getCanonicalName();

    public MyServiceHandler mHandler;

    private Looper serviceLooper;




    private class MyServiceHandler extends Handler {
        public MyServiceHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            Intent intent = (Intent) message.obj;
            int iteration = intent.getIntExtra(Ejercicio4Activity.ITERATION,-1);
            String msg = "(S) Processing iteration "+iteration;
            Intent response = new Intent(Ejercicio4MyService.RESPONSE_ACTION);
            response.putExtra(Ejercicio4MyService.RESPONSE,msg);
            response.putExtra(Ejercicio4Activity.WHO,ME);
            Log.d(TAG,msg);
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            LocalBroadcastManager.getInstance(Ejercicio4MyService.this).sendBroadcast(response);
            stopSelf(message.arg1);
        }
    }

    public Ejercicio4MyService() { super();
    }

    @Override
    public void onCreate(){
        HandlerThread thread = new HandlerThread("Ejercicio4MyServiceExample");
        thread.start();

        serviceLooper = thread.getLooper();
        mHandler = new MyServiceHandler(serviceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Message msg = mHandler.obtainMessage();
        msg.arg1 = startId;
        msg.obj = intent;

        // no recomendable
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.sendMessage(msg);
            }
        }).start();
        //mHandler.sendMessage(msg);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}