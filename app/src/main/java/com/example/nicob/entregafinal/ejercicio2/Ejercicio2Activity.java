package com.example.nicob.entregafinal.ejercicio2;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicob.entregafinal.R;

public class Ejercicio2Activity extends AppCompatActivity {

    private static final int GET_CONTACT = 1;
    private static final int GET_MULTI = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        Button b = findViewById(R.id.button_e2_1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setType(ContactsContract.Contacts.CONTENT_TYPE);
                startActivityForResult(i,GET_CONTACT);
            }
        });

        Button b2 = findViewById(R.id.button_e2_2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Ejercicio2Activity.this,Ejercicio2SecondaryActivity.class);
                setResult(RESULT_OK,i2);
                startActivityForResult(i2,GET_MULTI);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == GET_CONTACT){
            TextView tv = (TextView) findViewById(R.id.textView_e2_1);
            if (resultCode == RESULT_OK){
                String[] projection = {ContactsContract.Contacts.DISPLAY_NAME};

                // Un cursor es equivalente a un iterador
                Cursor c = getContentResolver().query(data.getData(),projection,null,null,null);
                c.moveToNext();
                String dat = data.getData().toString()+"\n";
                dat = dat +" + "+ c.getString(0);
                tv.setText(dat);
            }
            else {
                tv.setText(R.string.cancel);
            }
        }
        if (requestCode == GET_MULTI){
            TextView res = findViewById(R.id.textView_e2_2);
            Integer i = 0;
            int rex = data.getExtras().getInt("Resultado");
            res.setText(String.valueOf(rex));
        }
    }
}
