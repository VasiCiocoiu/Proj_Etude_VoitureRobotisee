package com.example.ihm.Bluetooth;


import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


//BluetoothClient se charge de la communication avec le robot
public class BluetoothClient extends Thread {

    private BluetoothDevice device = null;
    private BluetoothSocket bluetoothSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String nom;
    private String adresse;
    private boolean isAlive = true;

    public BluetoothClient( BluetoothDevice d ) {

        if(device != null) {
            this.device = d;
            this.nom = device.getName();
            this.adresse = device.getAddress();
        } else {
            this.device= d;
            this.nom = device.getName();
            this.adresse = "BluetoothDeco";
        }

        try {

            bluetoothSocket = device.createRfcommSocketToServiceRecord( device.getUuids()[0].getUuid() );
            bluetoothSocket.connect();

            inputStream = bluetoothSocket.getInputStream();
            outputStream = bluetoothSocket.getOutputStream();

            if(!isAlive) {
                Log.e( "DEBUG", device.getName() + "is dead");
            } else {
                Log.e( "DEBUG", device.getName() + "is alive");
            }

        } catch ( IOException exception ) {
            Log.e( "DEBUG", "Cannot establish connection", exception );
        }
    }


    // Inutile dans le code actuel. Mais cela permettrait de recevoir
    // des informations du véhicule dans une future version.
    @Override
    public void run() {
        try {
            while (isAlive) {
                if ( inputStream.available() > 0 ) {
                    Log.i("DEBUG", String.format("%c", inputStream.read()));
                } else {
                    Thread.sleep( 100 );
                }
            }
        } catch( Exception exception ) {
            Log.e( "DEBUG", "Cannot read data", exception );
            close();
        }
    }

    public void writeChar(char code) {
        try {
            outputStream.write( code );
            outputStream.flush();
        } catch (IOException e) {
            Log.e( "DEBUG", "Cannot write message", e );
        }
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }


    // Termine la connexion en cours et tue le thread
    public void close() {
        try {
            bluetoothSocket.close();
            isAlive = false;
        } catch (IOException e) {
            Log.e( "DEBUG", "Cannot close socket", e );
        }
        Log.e( "DEBUG", "La co est mort");
    }
}