package com.example.ihm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ihm.Bluetooth.Send;

//Menu d'accueil va renvoyer vers les differentes activité de l'application.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        CardView clickEnch = (CardView) findViewById(R.id.Card1);
        Intent intent = new Intent(this, Enchainement.class);

        /*
        CardView clickBluetooth = (CardView) findViewById(R.id.Card4);
        Intent send = new Intent(this, Send.class);
*/


        clickEnch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
/*
        clickBluetooth.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivity(send);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/


    }
}