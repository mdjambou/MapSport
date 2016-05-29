package com.team.mapsport;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.team.mapsport.adapter.Category;
import com.team.mapsport.adapter.Event;
import com.team.mapsport.adapter.Participant;
import com.team.mapsport.adapter.User;
import com.team.mapsport.business.CategoryRepository;
import com.team.mapsport.business.EventRepository;
import com.team.mapsport.business.ParticipantRepository;
import com.team.mapsport.common.CommonSetting;
import com.team.mapsport.common.SpinnerLoader;

import java.util.List;


public class AddEvent {

    private static int i = 0;

    public static void ajouterEvent(View myFragmentView, FragmentActivity myActivityView, User user) {

		final FragmentActivity activityThis = myActivityView;
        final View myFragmentViewThis = myFragmentView;
        final User userThis = user;

        // Create dialog Box
        final Dialog dialog = new Dialog(myActivityView);

        dialog.setContentView(R.layout.activity_create_event);
        dialog.setTitle("Make your move ");

        // Spinner element
        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinnerListCategories);
        List<String> catNames = SpinnerLoader.loadCategory(myActivityView);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(myActivityView, android.R.layout.simple_spinner_item, catNames);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //Select one radiobutton
        final RadioButton chkPrivate = (RadioButton) dialog.findViewById(R.id.chkPrivate);
        final RadioButton chkPublic = (RadioButton) dialog.findViewById(R.id.chkPublic);
        chkPrivate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // select only one radio button at any given time
                if (chkPrivate != null) {
                    chkPublic.setChecked(false);
                }
                chkPrivate.setChecked(true);
            }
        });
        chkPublic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // select only one radio button at any given time
                if (chkPublic != null) {
                    chkPrivate.setChecked(false);
                }
                chkPublic.setChecked(true);
            }
        });

        final TextView nbMembers = (TextView) dialog.findViewById(R.id.nbMembers);

        final String placesValue = nbMembers.getText().toString();
        final String[] places = placesValue.split(" ");
        final String nbMembValue = places[2];

        final Button addMembers = (Button) dialog.findViewById(R.id.addMembers);
        addMembers.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean add = true;
                i=i+1;
                nbMembers.setText(CommonSetting.SetupSizeEvent(nbMembValue, add, i));
            }
        });

        final Button removeMembers = (Button) dialog.findViewById(R.id.removeMembers);
        removeMembers.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean add = false;
                if(i<=0){
                    i=0;
                }else{
                    i = i-1;
                }
                nbMembers.setText(CommonSetting.SetupSizeEvent(nbMembValue, add, i));

            }
        });

        //DateTimePicker
        final Button	mDateDebutDisplay = (Button) dialog.findViewById(R.id.dateDisplay);
        mDateDebutDisplay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SetUpDateTime.setDateTime(myFragmentViewThis.getContext(), activityThis, mDateDebutDisplay);
            }
        });

        final Button	mDateFinDisplay = (Button) dialog.findViewById(R.id.dateFinDisplay);
        mDateFinDisplay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SetUpDateTime.setDateTime(myFragmentViewThis.getContext(), activityThis, mDateFinDisplay);
            }
        });

        // AddEvent ClickListener
        Button addEvent = (Button)dialog.findViewById(R.id.addEvent);
        addEvent.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                String categoryName = spinner.getSelectedItem().toString();
                CategoryRepository categoryRepository = new CategoryRepository(activityThis);
                categoryRepository.Open();
                Category cat = categoryRepository.GetId(categoryName, null, null);
                categoryRepository.Close();

                String eventName = ((EditText) dialog.findViewById(R.id.name)).getText().toString();

                boolean privateEvent = true;
                if (chkPublic.isChecked()) {
                    privateEvent = false;
                }

                final String placesValue = nbMembers.getText().toString();
                final String[] places = placesValue.split(" ");
                //int nbparticipant=  Integer.parseInt(places[0]);
                int sizeEvent = Integer.parseInt(places[2]);

                String descript = ((EditText) dialog.findViewById(R.id.details)).getText().toString();
                String location = ((EditText) dialog.findViewById(R.id.location)).getText().toString();

                String dateDebEvent = mDateDebutDisplay.getText().toString();
                String dateFinEvent = mDateFinDisplay.getText().toString();

                if (eventName == null || descript == null || location == null || dateDebEvent == null || dateFinEvent == null) {
                    Toast.makeText(activityThis.getApplicationContext(), " Some informations are missing !!", Toast.LENGTH_SHORT).show();
                } else {
                    Event event = new Event(eventName, cat.getId(), privateEvent, sizeEvent, descript, location, dateDebEvent, dateFinEvent);
                    EventRepository eventRepository = new EventRepository(activityThis);
                    eventRepository.Open();
                    long idEvent = eventRepository.Save(event);
                    eventRepository.Close();

                    Bundle infoUser = activityThis.getIntent().getExtras();
                    if (infoUser.get("userID") != null) {
                        String userID = String.valueOf(infoUser.get("userID"));
                        long idUser = Long.parseLong(userID);
                        Participant participant = new Participant(idUser, idEvent);
                        ParticipantRepository participantRepository = new ParticipantRepository(activityThis);
                        participantRepository.Open();
                        participantRepository.Save(participant);
                        participantRepository.Close();

                       /* Intent intent = new Intent(ConnexionActivity.this, TabSwipeMainActivity.class);
                        intent = CommonSetting.setUserBundle(user,intent);
                        startActivityForResult(intent, 1);*/

                    } else {
                        Toast.makeText(activityThis.getApplicationContext(), " User id error !!", Toast.LENGTH_SHORT).show();
                    }

                }
                dialog.dismiss();
                EventList.afficherEvents(myFragmentViewThis, activityThis, userThis);

            }
        });
        Button buttonCancel = (Button)dialog.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clic sur le bouton annuler
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
