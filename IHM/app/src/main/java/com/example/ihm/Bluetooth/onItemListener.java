/*package com.example.ihm.Bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


//onItemListener permet de choisir le reseaux bluetooth auquel je souhaite me connecter et va le stocker dans la variable device avant d'etre envoyer a bluetooth client
public class onItemListener implements ListView.OnItemClickListener{

    private List<BluetoothDevice> knownDevices ;
    private BluetoothClient bluetoothClient;
    private TextView lblConnectedDevice;

    public onItemListener(List<BluetoothDevice> devices, BluetoothClient bC, TextView CoDev ){
        this.knownDevices = devices;
        this.bluetoothClient = bC;
        this.lblConnectedDevice = CoDev;
    }

    @Override public void onItemClick(AdapterView<?> adapter, View view, int arg2, long rowId) {
        BluetoothDevice device = knownDevices.get( (int) rowId );
        bluetoothClient = new BluetoothClient( device );
        lblConnectedDevice.setText( "Connected to " + device.getName() );
    }
}
*/