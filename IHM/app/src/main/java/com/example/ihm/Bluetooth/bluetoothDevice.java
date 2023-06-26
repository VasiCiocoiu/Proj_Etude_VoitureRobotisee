package com.example.ihm.Bluetooth;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.ihm.R;


public class bluetoothDevice extends Fragment {

    private ListView deviceList;
    //private List<BluetoothDevice> list ;
    private OnItemListener callBack;

    private static final int REQUEST_ENABLE_BT = 456;

    private BluetoothAdapter bluetoothAdapter ;
    // private BluetoothClient bluetoothClient;
    private List<BluetoothDevice> knownDevices ;



    public interface OnItemListener {
        public void onItemClicked(AdapterView<?> adapter, View view, int arg2, long rowId, List<BluetoothDevice> list);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bluetooth_device,container, true);



        deviceList = rootView.findViewById(R.id.deviceList);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if ( ! bluetoothAdapter.isEnabled() )  {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }



        knownDevices = new ArrayList<>( bluetoothAdapter.getBondedDevices() );
        ArrayAdapter<BluetoothDevice> adapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, knownDevices);
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener( deviceListListener );

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private ListView.OnItemClickListener deviceListListener = new ListView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> adapter, View view, int arg2, long rowId) {
            callBack.onItemClicked(adapter, view, arg2, rowId, knownDevices);


        }
    };

    private void createCallbackToParentActivity() {
        try {
            callBack = (OnItemListener) getActivity();
        } catch(Exception e) {
            Log.e( "DEBUG", "Pb callback", e );
        }
    }

}