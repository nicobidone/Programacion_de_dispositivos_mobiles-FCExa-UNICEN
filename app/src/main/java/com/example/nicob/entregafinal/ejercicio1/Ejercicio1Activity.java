package com.example.nicob.entregafinal.ejercicio1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicob.entregafinal.MainActivity;
import com.example.nicob.entregafinal.R;

public class Ejercicio1Activity extends AppCompatActivity {

    private String TAG = MainActivity.class.getCanonicalName();
    private int a = 0;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        Button boton = (Button) findViewById(R.id.button_e1_1);  //Con R referencio a los resources
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "El boton fue presionado");
                System.out.println(R.id.textView_e1_1);
                TextView texto = (TextView) findViewById(R.id.textView_e1_1);
                texto.setText("El boton fue presionado " + a + " veces");
                a++;

                Context context = getApplicationContext();
                CharSequence text = "Hola mundo!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
