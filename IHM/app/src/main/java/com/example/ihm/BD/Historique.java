package com.example.ihm.BD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihm.R;

public class Historique extends AppCompatActivity {
    private final static String[] CHAMPS = {BaseDeDonnees.CHAMP_PATH};
    private final static int[] VUES = { R.id.nom};
    private SimpleCursorAdapter adaptateur;
    private Cursor curseur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        this.adaptateur = new SimpleCursorAdapter(this, R.layout.activity_historique, null, Historique.CHAMPS, Historique.VUES, 0);
        ListView elements = (ListView) this.findViewById(R.id.elements);
        elements.setAdapter(this.adaptateur);
        BaseDeDonnees acces = new BaseDeDonnees(this);
        SQLiteDatabase baseDeDonnees = acces.getReadableDatabase();
        curseur = baseDeDonnees.query(BaseDeDonnees.NOM_TABLE, null, null, null, null, null, null);
        TextView editText = (TextView) findViewById(R.id.editText);

        if ( this.remplirListe()){
            Log.e("DEBUG","remplireListe OK");
        }else {
            Log.e("DEBUG","NOT OK");
        }

        elements.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SQLiteCursor s = (SQLiteCursor) parent.getItemAtPosition(position);
                String data = s.getString(1);
                editText.setText(data);
            }
        });

        elements.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {

                SQLiteCursor s = (SQLiteCursor) parent.getItemAtPosition(position);
                String text = s.getString(1);

                if (text.length() > 0){
                    if (acces.deletePath(text)){
                        Log.e("DEBUG","DELETE OK");
                    }else {
                        Log.e("DEBUG","DELETE NOT OK");
                    }
                }

                SQLiteDatabase baseDeDonnees = acces.getReadableDatabase();
                curseur = baseDeDonnees.query(BaseDeDonnees.NOM_TABLE, null, null, null, null, null, null);
                remplirListe();

                return true;

            }
        });

    }

    public boolean remplirListe() {
        if (this.curseur != null) {

            this.adaptateur.swapCursor(this.curseur);
        } else {
            Toast.makeText(this, "Erreur d'accès à la base de données", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}