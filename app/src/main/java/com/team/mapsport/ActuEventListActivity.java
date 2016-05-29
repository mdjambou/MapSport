package com.team.mapsport;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team.mapsport.adapter.User;
import com.team.mapsport.common.CommonSetting;

public class ActuEventListActivity extends Fragment {
	private View myFragmentView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		myFragmentView = inflater.inflate(R.layout.activity_actu_event_list, container, false);
		//action bar
		//CommonSetting.actionBarSetting(this.getActivity().getActionBar());

		Bundle infoUser = this.getActivity().getIntent().getExtras();
		final User user = CommonSetting.getUserBundle(infoUser);

        final FragmentActivity fragAct = this.getActivity();

		EventList.afficherEvents(myFragmentView, this.getActivity(), user);

       /* Button addMyEvent = (Button) myFragmentView.findViewById(R.id.addMyEvent);
        addMyEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                AddEvent.ajouterEvent(myFragmentView, fragAct, user);

            }

         });*/

		FloatingActionButton addMyEvent = (FloatingActionButton) myFragmentView.findViewById(R.id.fabAddEvent);
		addMyEvent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AddEvent.ajouterEvent(myFragmentView, fragAct, user);
			}
		});

		return myFragmentView;
	}

}
