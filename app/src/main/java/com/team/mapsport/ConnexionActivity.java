package com.team.mapsport;

//import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team.mapsport.adapter.User;
import com.team.mapsport.business.UserRepository;
import com.team.mapsport.common.CommonSetting;
import com.team.mapsport.tabsswipe.TabSwipeMainActivity;

public class ConnexionActivity extends AppCompatActivity {
    
    private static final String SENDER_ID = "779086340353";
    private Button connecter;
    private UserRepository userRepository;
    EditText mail;
    EditText mdp;
    User user = null;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_connexion);

          //action bar

            ActionBar actionBar= this.getSupportActionBar();
            actionBar.setTitle("Se connecter");

            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fond_vert));

            User userInfos = CommonSetting.getUserBundle(this.getIntent().getExtras());
            if(userInfos != null){
                ((EditText)findViewById(R.id.email)).setText(userInfos.getEmail());
                ((EditText)findViewById(R.id.mdp)).setText(userInfos.getPassword());
            }

            //Connexion part
            mail = (EditText)findViewById(R.id.email);
            mdp = (EditText)findViewById(R.id.mdp);

            userRepository = new UserRepository(this);
            connecter = (Button)findViewById(R.id.connecter);
            connecter.setOnClickListener(new OnClickListener(){
                public void onClick(View view){
                    String monEmail = mail.getText().toString();
                    String monMDP =mdp.getText().toString();
                    if(monEmail == null || monEmail.equals("") || !CommonSetting.emailValide(monEmail)){
                        Toast.makeText(getApplicationContext(), "Please, enter a correct email address !!", Toast.LENGTH_SHORT).show();
                    }else if( monMDP== null || monMDP.equals("")){
                        Toast.makeText(getApplicationContext(), "Please, enter your password !!", Toast.LENGTH_SHORT).show();
                    }else if( CommonSetting.emailValide(monEmail)){
                        userRepository.Open();
                        user = userRepository.GetByMainAttribute(monEmail);
                        //userRepository.Update(user);
                        userRepository.Close();

                        if(user != null && user.getPassword()!= null && user.getPassword().equals(monMDP)){
                            Intent intent = new Intent(ConnexionActivity.this, TabSwipeMainActivity.class);
                            intent = CommonSetting.setUserBundle(user, intent);
                            startActivityForResult(intent,1);
                        }
                        else{;
                            Toast.makeText(getApplicationContext(), "Wrong Email address or Password  !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Wrong Email address !!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    // La ligne de code ci-dessous permet d'activité le bouton retour
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }

        private void setupActionBar() {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
}