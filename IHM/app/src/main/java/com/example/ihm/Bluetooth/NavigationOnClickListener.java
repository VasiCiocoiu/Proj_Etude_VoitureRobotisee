package com.example.ihm.Bluetooth;

import android.view.View;
import android.widget.TextView;

import com.example.ihm.R;

public class NavigationOnClickListener implements View.OnClickListener {

    private TextView editTxt;
    private int vitesse = 0;
    private int position;
    private String code = "";


    //Quand le avec bluetoothClient sera résolu le choix de mouvement se fera dans le listener
    public NavigationOnClickListener(TextView txt){
        this.editTxt = txt;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.imageButton3:
                try {
                    editTxt.setText(editTxt.getText() + "↑");
                    code = code+"f";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.diaGauche:
                try {
                    editTxt.setText(editTxt.getText() + "⇖" );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.diagDroite:
                try {
                    editTxt.setText(editTxt.getText() + "⇗" );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.imageButton5:
                try {
                    editTxt.setText(editTxt.getText() + "↓");
                    code = code+"f";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.imageButton4:
                try {
                    editTxt.setText(editTxt.getText() + "→");
                    code = code+"f";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.imageButton2:
                try {
                    editTxt.setText(editTxt.getText() + "←");
                    code = code+"f";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.suppr:
                try {
                    editTxt.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.effacer:
                try {
                    position = editTxt.getText().length();
                    StringBuilder str = new StringBuilder(editTxt.getText());
                    str = str.deleteCharAt(position-1);
                    editTxt.setText(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }
}