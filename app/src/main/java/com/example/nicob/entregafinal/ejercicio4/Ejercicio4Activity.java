package com.example.nicob.entregafinal.ejercicio4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicob.entregafinal.R;

public class Ejercicio4Activity extends AppCompatActivity {

    public static final String ITERATION = "iteration";
    public static final String WHO = "who";

    private TextView isTextA;
    private TextView isTextB;
    private TextView memText;

    private LocalReciever reciever = new LocalReciever();

    private class LocalReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(Ejercicio4MyIntentService.RESPONSE);
            int who = intent.getIntExtra(WHO,-1);
            switch (who){
                case Ejercicio4MyIntentService.ME:
                    isTextA.setText(msg);
                    break;
                case Ejercicio4MyService.ME:
                    isTextB.setText(msg);
                    break;
                case Ejercicio5MessengerService.ME:
                    memText.setText(msg);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio4);

        isTextA = (TextView) findViewById(R.id.textView_e4_1);
        isTextB = (TextView) findViewById(R.id.textView_e4_2);
        memText = (TextView) findViewById(R.id.textView_e5_1);

        Button b = (Button) findViewById(R.id.button_e4_1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < 4; i++){
                    Intent mServiceIntent = new Intent(Ejercicio4Activity.this,Ejercicio4MyIntentService.class);
                    mServiceIntent.putExtra(ITERATION,i);
                    startService(mServiceIntent);
                }
            }
        });

        Button b2 = findViewById(R.id.button_e4_2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < 4; i++){
                    Intent mServiceIntent = new Intent(Ejercicio4Activity.this,Ejercicio4MyService.class);
                    mServiceIntent.putExtra(ITERATION,i);
                    startService(mServiceIntent);
                }
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(reciever,new IntentFilter(Ejercicio4MyIntentService.RESPONSE_ACTION));
    }

    @Override
    protected void onStop(){
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(reciever);
    }
}
