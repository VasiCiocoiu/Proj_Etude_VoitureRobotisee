package com.example.ihm.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class BaseDeDonnees extends SQLiteOpenHelper {
    public final static String NOM_BASE = "base_historique";
    public final static int VERSION_BASE = 3;
    public final static String NOM_TABLE = "historique";
    public final static String CHAMP_PATH = "historique_chemin";

    public BaseDeDonnees(Context context) {
        super(context, BaseDeDonnees.NOM_BASE, null, BaseDeDonnees.VERSION_BASE);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDeDonnees) {
        baseDeDonnees.execSQL("create table " + BaseDeDonnees.NOM_TABLE + "(" +
                BaseColumns._ID + " integer primary key autoincrement, "  +
                BaseDeDonnees.CHAMP_PATH + " text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase baseDeDonnees, int vieux, int nouveau) {
        baseDeDonnees.execSQL("drop table if exists " + BaseDeDonnees.NOM_TABLE);
        this.onCreate(baseDeDonnees);
    }

    public boolean addPath(String txt){
        ContentValues tuple = new ContentValues();
        tuple.put(BaseDeDonnees.CHAMP_PATH, txt);
        SQLiteDatabase baseDeDonnees = this.getWritableDatabase();
        baseDeDonnees.insert(BaseDeDonnees.NOM_TABLE, null, tuple);
        baseDeDonnees.close();
        return true;
    }
    public boolean deletePath(String txt){
        ContentValues tuple = new ContentValues();
        tuple.put(BaseDeDonnees.CHAMP_PATH, txt);
        SQLiteDatabase baseDeDonnees = this.getWritableDatabase();
        baseDeDonnees.delete(NOM_TABLE,"historique_chemin=?",new String[]{txt});
        baseDeDonnees.close();
        return true;
    }
}
