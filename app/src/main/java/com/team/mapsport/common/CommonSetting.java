package com.team.mapsport.common;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import com.team.mapsport.adapter.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonSetting {
	     
	public static void actionBarSetting(ActionBar action){
		action.setHomeButtonEnabled(true);
		action.setTitle("D.I.Now");	       
	}

    public static boolean emailValide(String email){
        Pattern p =Pattern.compile("[a-zA-Z0-9_.]*@[a-zA-Z]*.[a-zA-Z]*");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static User getUserBundle( Bundle infoUser){
        User user = null;
        if(infoUser != null){
            Long id = infoUser.getLong("userID");
            String email = infoUser.getString("email");
            String pwd = infoUser.getString("password");
            String pseudo = infoUser.getString("pseudo");
            user = new User (pseudo, pwd, email);
            if(id!=null && id!= 0){
                user.setIdentifiant(id);
            }
        }
        return user;
    }

    public static Intent setUserBundle( User user, Intent intent){
        intent.putExtra("userID", user.getIdentifiant());
        intent.putExtra("pseudo", user.getPseudo());
        intent.putExtra("password", user.getPassword());
        intent.putExtra("email", user.getEmail());
        return intent;
    }

    public static Intent setUserBundle( String email, String password,String pseudo, Intent intent){
        intent.putExtra("pseudo", pseudo);
        intent.putExtra("password", password);
        intent.putExtra("email", email);
        return intent;
    }
    public static String SetupSizeEvent (String nbMembValue, boolean add, int i) {
        int nb = Integer.parseInt(nbMembValue);
        String nbPlaces="";
        nb = nb + i;
        return "1 / "+Integer.toString(nb) + " participants";
    }


    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
   

}
