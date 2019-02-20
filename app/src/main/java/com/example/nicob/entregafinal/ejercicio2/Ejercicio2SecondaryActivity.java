package com.example.nicob.entregafinal.ejercicio2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicob.entregafinal.R;

public class Ejercicio2SecondaryActivity extends AppCompatActivity {

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2_secondary);

        Button boton = findViewById(R.id.button_e2_3);  //Con R referencio a los resources

        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Integer num1 = Integer.parseInt(((TextView)findViewById(R.id.editText_e2_1)).getText().toString()),
                        num2 = Integer.parseInt(((TextView)findViewById(R.id.editText_e2_2)).getText().toString());

                Intent i2 = new Intent();
                i2.putExtra("Resultado",num1*num2);
                setResult(Ejercicio2SecondaryActivity.RESULT_OK,i2);
                finish();
            }
        });
    }
}
