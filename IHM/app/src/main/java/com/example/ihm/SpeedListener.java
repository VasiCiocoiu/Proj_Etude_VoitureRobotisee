package com.example.ihm;

import android.view.View;
import android.widget.ImageButton;

public class SpeedListener implements View.OnClickListener {

    private int vitesse = 0;
    private ImageButton clickVitesse;

    public SpeedListener(ImageButton clickVitesse){
        this.clickVitesse = clickVitesse;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.vitesse:

                if (vitesse == 0) {
                    clickVitesse.setImageResource(R.drawable.vitesse);
                    //editTxt.setTextColor(Color.rgb(255,200,0));
                    vitesse ++;
                } else if (vitesse == 1) {
                    clickVitesse.setImageResource(R.drawable.vitesse2);
                    //editTxt.setTextColor(Color.rgb(255,100,0));
                    vitesse ++;
                } else {
                    clickVitesse.setImageResource(R.drawable.vitesse3);
                    //editTxt.setTextColor(Color.rgb(255,0,0));
                    vitesse = 0;
                }
                break;
        }
    }
}