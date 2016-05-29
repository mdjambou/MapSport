package com.team.mapsport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team.mapsport.adapter.User;
import com.team.mapsport.business.UserRepository;
import com.team.mapsport.common.CommonSetting;
import com.team.mapsport.tabsswipe.TabSwipeMainActivity;

public class InscriptionActivity extends Activity
{

    private Button inscrire, signInRedirect;
    EditText mail,mdp,pseudo,lastName,firstName;
    private UserRepository userRepository;
    private String monPassword,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        //action bar
      // this.getActionBar().hide();


        User userInfos = CommonSetting.getUserBundle(this.getIntent().getExtras());
        if(userInfos != null){
            ((EditText)findViewById(R.id.email)).setText(userInfos.getEmail());
            ((EditText)findViewById(R.id.mdp)).setText(userInfos.getPassword());
            ((EditText)findViewById(R.id.pseudo)).setText(userInfos.getPseudo());
        }

        userRepository = new UserRepository(this);

        inscrire = (Button) findViewById(R.id.inscrire);
        inscrire.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String monEmail = ((EditText) findViewById(R.id.email)).getText().toString();
                String monMDP = ((EditText) findViewById(R.id.mdp)).getText().toString();
                String monPseudo = ((EditText) findViewById(R.id.pseudo)).getText().toString();
                String monLastName = ((EditText) findViewById(R.id.lastname)).getText().toString();
                String monFistName = ((EditText) findViewById(R.id.firstname)).getText().toString();

                if ((monPseudo != null) && (monEmail != null) && (monMDP != null) && (CommonSetting.emailValide(monEmail))) {
                    userRepository.Open();
                    if (userRepository.GetId(monPseudo, null, monEmail) == null) {
                        User user = new User(monPseudo, monMDP, monEmail);
                        user.setFirstName(monFistName);
                        user.setLastName(monLastName);
                        long id  = userRepository.Save(user);
                        user.setIdentifiant(id);
                        userRepository.Close();
                        Intent intent = new Intent(InscriptionActivity.this, TabSwipeMainActivity.class);
                        intent = CommonSetting.setUserBundle(user, intent);
                        startActivityForResult(intent, 1);
                    } else {
                        Toast.makeText(getApplicationContext(), "Email or Password already exists !!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password !!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        signInRedirect = (Button) findViewById(R.id.signinredirect);
        signInRedirect.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(InscriptionActivity.this, ConnexionActivity.class);
                intent.putExtra("email", ((EditText) findViewById(R.id.email)).getText().toString());
                intent.putExtra("password", ((EditText) findViewById(R.id.mdp)).getText().toString());
                startActivityForResult(intent, 1);
            }
        });
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_inscription, menu);
        return true;
    }*/

}
