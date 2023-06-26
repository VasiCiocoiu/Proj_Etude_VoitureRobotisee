package com.example.ihm.Bluetooth;
/*
import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihm.Enchainement;
import com.example.ihm.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Send extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 456;

    private BluetoothAdapter bluetoothAdapter ;
    private BluetoothClient bluetoothClient = null;
    private List<BluetoothDevice> knownDevices ;

    private TextView lblConnectedDevice;

    private Button btnForward;
    private Button btnStop;
    private Button btnBackward;
    private Button btnLeft;
    private Button btnRight;
    private String code = "";
    private TextView editText;


    private ListView deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);


        lblConnectedDevice = findViewById( R.id.lblConnectedDevice);
        this.editText = (TextView) findViewById(R.id.textView2);


        btnForward = findViewById( R.id.btnForward );
        btnForward.setOnClickListener( buttonsListener);
        btnStop = findViewById( R.id.btnStop );
        btnBackward = findViewById( R.id.btnBackward );
        btnBackward.setOnClickListener( buttonsListener );
        btnLeft = findViewById( R.id.btnLeft );
        btnLeft.setOnClickListener(   buttonsListener );
        btnRight = findViewById( R.id.btnRight );
        btnRight.setOnClickListener( buttonsListener );


        deviceList = findViewById( R.id.deviceList);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if ( ! bluetoothAdapter.isEnabled() )  {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }*/

   /*   BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        List<String> s = new ArrayList<String>();
        for(BluetoothDevice bt : pairedDevices)
            s.add(bt.getName());

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list, s));*/

        //Ajoute l'ensemble des reseaux bluetooth auxquelles le telephone est appairé a une liste
     /*   knownDevices = new ArrayList<>( bluetoothAdapter.getBondedDevices() );
        ArrayAdapter<BluetoothDevice> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, knownDevices);
        deviceList.setAdapter(adapter);


        //Listener pour le choix du bluetooth
        onItemListener itemListener = new onItemListener(knownDevices, bluetoothClient, lblConnectedDevice);
        deviceList.setOnItemClickListener( deviceListListener );

        btnStop.setOnClickListener( bluetoothListener );


    }*/

 /*   private ListView.OnItemClickListener deviceListListener = new ListView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> adapter, View view, int arg2, long rowId) {
            BluetoothDevice device = knownDevices.get( (int) rowId );
            bluetoothClient = new BluetoothClient( device );

            String msg = bluetoothClient.getNom() + " - " + bluetoothClient.getAdresse();
            Toast.makeText(Send.this, msg, Toast.LENGTH_SHORT).show();*/

           /* Intent intent = new Intent(Send.this, Enchainement.class);
            intent.putExtra(BluetoothDevice.EXTRA_DEVICE, device);
            startActivity(intent);*/

   /*         lblConnectedDevice.setText( "Connected to " + device.getName() );
        }
    };*/


  /*  private View.OnClickListener buttonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch( view.getId() ) {
                case R.id.btnForward:
                    code = code+"f";
                    break;

                case R.id.btnBackward:
                    code =code+"b";
                break;

                case R.id.btnLeft:
                    code = code+"l";
                break;

                case R.id.btnRight:
                    code = code+"r";
                break;
            }

        }
    };

    private View.OnClickListener bluetoothListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

           for (int i = 0;i<code.length();i++){
                bluetoothClient.writeChar(code.charAt(i));
                SystemClock.sleep(2000);
            }
            code="";
        }
    };

}*/


import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihm.Enchainement;
import com.example.ihm.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class Send extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 456;

    private BluetoothAdapter bluetoothAdapter ;
    private BluetoothClient bluetoothClient;
    private List<BluetoothDevice> knownDevices ;

    private TextView lblConnectedDevice;

    private Button btnForward;
    private Button btnStop;
    private Button btnBackward;
    private Button btnLeft;
    private Button btnRight;
    private String code = "";
    private TextView editText;


    private ListView deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        lblConnectedDevice = findViewById( R.id.lblConnectedDevice);
        this.editText = (TextView) findViewById(R.id.textView2);

        btnForward = findViewById( R.id.btnForward );
        btnForward.setOnClickListener( buttonsListener);
        btnStop = findViewById( R.id.btnStop );
        btnBackward = findViewById( R.id.btnBackward );
        btnBackward.setOnClickListener( buttonsListener );
        btnLeft = findViewById( R.id.btnLeft );
        btnLeft.setOnClickListener( buttonsListener );
        btnRight = findViewById( R.id.btnRight );
        btnRight.setOnClickListener( buttonsListener );

        deviceList = findViewById( R.id.deviceList);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if ( ! bluetoothAdapter.isEnabled() )  {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        knownDevices = new ArrayList<>( bluetoothAdapter.getBondedDevices() );
        ArrayAdapter<BluetoothDevice> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, knownDevices);
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener( deviceListListener );

        btnStop.setOnClickListener( bluetoothListener );

    }

    private ListView.OnItemClickListener deviceListListener = new ListView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> adapter, View view, int arg2, long rowId) {
            BluetoothDevice device = knownDevices.get( (int) rowId );
            bluetoothClient = new BluetoothClient( device );
            lblConnectedDevice.setText( "Connected to " + device.getName() );

        }
    };

    private View.OnClickListener buttonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch( view.getId() ) {
                case R.id.btnForward:
                    code = code+"f";
                    break;

                case R.id.btnBackward:
                    code =code+"b";
                    break;

                case R.id.btnLeft:
                    code = code+"l";
                    break;

                case R.id.btnRight:
                    code = code+"r";
                    break;
            }

        }
    };
    private View.OnClickListener bluetoothListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            for (int i = 0;i<code.length();i++){
                bluetoothClient.writeChar(code.charAt(i));
                SystemClock.sleep(1000);
            }
            code="";
        }
    };
}
