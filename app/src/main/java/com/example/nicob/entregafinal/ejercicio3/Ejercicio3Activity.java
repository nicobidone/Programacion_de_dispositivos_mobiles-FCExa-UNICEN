package com.example.nicob.entregafinal.ejercicio3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicob.entregafinal.MainActivity;
import com.example.nicob.entregafinal.R;

public class Ejercicio3Activity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private boolean active = false;
    int counter = 0;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);

        final Button start = (Button) findViewById(R.id.button_e3_1);
        final Button stop = (Button) findViewById(R.id.button_e3_2);
        final Button reset = (Button) findViewById(R.id.button_e3_3);
        final TextView time = (TextView) findViewById(R.id.textView_e3_1);

        final SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        counter = prefs.getInt("c", 0); //0 is the default value.
        time.setText(String.valueOf(counter));

        stop.setEnabled(false);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active = true;
                stop.setEnabled(true);
                start.setEnabled(false);

                AsyncTask<?,?,?> asyncTask = new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object... objects) {
                        while(active){
                            try{
                                synchronized (Ejercicio3Activity.this){
                                    Ejercicio3Activity.this.wait(1000);
                                }
                                if (!active)
                                    break;
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            publishProgress(counter++);
                            Log.d(TAG,"Van "+counter+" segundos");
                        }
                        return null;
                    }

                    @Override
                    protected void onProgressUpdate(Object... values) {
                        super.onProgressUpdate(values);
                        time.setText(String.valueOf(values[0]));
                    }
                };
                asyncTask.execute();

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active = false;
                stop.setEnabled(false);
                start.setEnabled(true);
                time.setText(String.valueOf(counter));
                synchronized (Ejercicio3Activity.this){
                    Ejercicio3Activity.this.notifyAll();
                }
                editor.putInt("c",counter);
                editor.apply();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active = false;
                stop.setEnabled(false);
                start.setEnabled(true);
                counter = 0;
                time.setText(String.valueOf(counter));
                synchronized (Ejercicio3Activity.this){
                    Ejercicio3Activity.this.notifyAll();
                }
                editor.putInt("c",counter);
                editor.apply();
            }
        });
    }
}
