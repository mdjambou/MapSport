package com.team.mapsport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.team.mapsport.business.UserRepository;


public class MainActivity extends Fragment {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	ToggleButton tButtonUserStatus  ;
	TextView tvStateofToggleButton;
	TextView descript;		
	ImageButton addActivities;       
	private View myFragmentView;
	private static UserRepository userRepository;
    private View myFragmentViewAlerte;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		myFragmentView = inflater.inflate(R.layout.activity_main, container, false);
		//action bar
       /* CommonSetting.actionBarSetting(this.getActivity().getActionBar());

    	Bundle infoUser = this.getActivity().getIntent().getExtras();
        final User user = CommonSetting.getUserBundle(infoUser);


        EventList.afficherEvents(myFragmentView, this.getActivity(), user);
*/
		return myFragmentView;
	}


}
