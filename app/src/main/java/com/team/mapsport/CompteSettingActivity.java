package com.team.mapsport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompteSettingActivity extends Fragment
{

	private View myFragmentView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		myFragmentView = inflater.inflate(R.layout.activity_compte_user, container, false);
	            
      //action bar
       //CommonSetting.actionBarSetting(this.getActivity().getActionBar());
        

   	return myFragmentView;

    }
    
}
