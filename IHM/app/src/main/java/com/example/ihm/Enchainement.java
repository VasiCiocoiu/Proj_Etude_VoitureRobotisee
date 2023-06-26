package com.example.ihm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.AdapterView;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihm.BD.AddPathListener;
import com.example.ihm.BD.BaseDeDonnees;
import com.example.ihm.BD.Historique;
import com.example.ihm.Bluetooth.BluetoothClient;
import com.example.ihm.Bluetooth.NavigationOnClickListener;
import com.example.ihm.Bluetooth.bluetoothDevice;

import java.util.List;


//Cette activité permet a l'utilisateur de choisir un enchainement de mouvement et de l'envoyer au robot
public class Enchainement extends AppCompatActivity implements bluetoothDevice.OnItemListener {

    private String code = "";
    private BluetoothClient bluetoothClient;
    private TextView editText;
    private bluetoothDevice bluetoothDevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
            }
        }

        setContentView(R.layout.activity_enchainement);


        this.editText = (TextView) findViewById(R.id.editText);

        //Initialisation des composants
        ImageButton clickLeft = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton clickUp = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton clickRight = (ImageButton) findViewById(R.id.imageButton4);
        ImageButton clickDown = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton clickVitesse = (ImageButton) findViewById(R.id.vitesse);
        ImageButton clickSuppr = (ImageButton) findViewById(R.id.suppr);
        ImageButton clickEffacer = (ImageButton) findViewById(R.id.effacer);
        ImageButton clickDiagG = (ImageButton) findViewById(R.id.diaGauche);
        ImageButton clickDiagD = (ImageButton) findViewById(R.id.diagDroite);

        ImageButton clickSend = (ImageButton) findViewById(R.id.imageButton8);


        //Initialisation d'un listener pour les boutons
        NavigationOnClickListener listener = new NavigationOnClickListener(editText);

        clickLeft.setOnClickListener(buttonsListener);
        clickUp.setOnClickListener(buttonsListener);
        clickRight.setOnClickListener(buttonsListener);
        clickDown.setOnClickListener(buttonsListener);
        clickSuppr.setOnClickListener(listener);
        clickEffacer.setOnClickListener(listener);
        clickDiagG.setOnClickListener(listener);
        clickDiagD.setOnClickListener(listener);

        //Initialisation d'un listener pour le changement de vitesse
        SpeedListener speedlistener = new SpeedListener(clickVitesse);
        clickVitesse.setOnClickListener(speedlistener);

        //Initialisation d'un listener pour envoyer au robot l'enchainement d'instruction
        clickSend.setOnClickListener(bluetoothListener);


        ImageButton save = (ImageButton) findViewById(R.id.button_save);
        BaseDeDonnees acces = new BaseDeDonnees(this);
        AddPathListener saveListener = new AddPathListener(editText, acces);
        save.setOnClickListener(saveListener);


    }


    //Listener qui ajoute chaque mouvement a un String
    private View.OnClickListener buttonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageButton3:
                    editText.setText(editText.getText() + "↑");
                    code = code + "f";
                    Enchainement.this.finish();
                    break;

                case R.id.imageButton5:
                    editText.setText(editText.getText() + "↓");
                    code = code + "b";
                    break;

                case R.id.imageButton2:
                    editText.setText(editText.getText() + "←");
                    code = code + "l";
                    break;

                case R.id.imageButton4:
                    editText.setText(editText.getText() + "→");
                    code = code + "r";
                    break;
            }
        }
    };


    //Erreur car le bluetooth client est deconnecté de l'activité
    private View.OnClickListener bluetoothListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            for (int i = 0; i < code.length(); i++) {
                Log.e("DEBUG", String.valueOf(code.charAt(i)));
                bluetoothClient.writeChar(code.charAt(i));
                SystemClock.sleep(2000);
            }
            code = "";
        }
    };


    @Override
    protected void onStart() {
        super.onStart();


        ImageButton clickHisto = (ImageButton) findViewById(R.id.imageButton6);
        Intent histo = new Intent(this, Historique.class);


        //Lancement de nouvelles activités
        clickHisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivity(histo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onItemClicked(AdapterView<?> adapter, View view, int arg2, long rowId, List<BluetoothDevice> knowDevices) {

        Log.e("DEBUG", String.valueOf(rowId));
        BluetoothDevice device = knowDevices.get((int) rowId);
        bluetoothClient = new BluetoothClient(device);


        Fragment fragment;
        fragment = getSupportFragmentManager().findFragmentById(R.id.frag);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment).commit();


        //view.setVisibility(View.GONE);
    }

}