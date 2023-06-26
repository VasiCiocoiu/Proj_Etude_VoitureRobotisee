package com.example.ihm.BD;

import android.content.Context;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihm.Enchainement;


public class AddPathListener implements View.OnClickListener{
    private BaseDeDonnees acces;
    private TextView editTxt;

    public AddPathListener(TextView txt, BaseDeDonnees bdd){
        this.acces = bdd;
        this.editTxt = txt;
    }
    @Override
    public void onClick(View view) {
        String str = editTxt.getText().toString();
        if (str.length() > 0){
            if (acces.addPath(str)){
                Log.e("DEBUG","OK");
            }else {
                Log.e("DEBUG","ADD NOT OK");
            }
        }

    }
}
