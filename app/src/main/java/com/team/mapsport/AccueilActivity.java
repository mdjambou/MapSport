package com.team.mapsport;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AccueilActivity extends AppCompatActivity {

    private Button connecter;
    private Button inscrire;
    private FloatingActionButton mode_visiteur;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

      //action bar
      //CommonSetting.actionBarSetting(this.getActionBar());
        this.getSupportActionBar().hide();

        connecter = (Button)findViewById(R.id.connecter);
        connecter.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AccueilActivity.this, ConnexionActivity.class);
                startActivityForResult(intent,1);
            }
        });

        inscrire = (Button)findViewById(R.id.inscrire);
        inscrire.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AccueilActivity.this, InscriptionActivity.class);
                startActivityForResult(intent,1);
            }
        });

        mode_visiteur = (FloatingActionButton)findViewById(R.id.mode_visiteur);
        mode_visiteur.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
        /*        Intent intent = new Intent(AccueilActivity.this, MapEquipementActivity.class);
                startActivityForResult(intent,1);*/
            }
        });
  
    }
}