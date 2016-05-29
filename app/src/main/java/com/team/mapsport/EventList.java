package com.team.mapsport;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.team.mapsport.adapter.Event;
import com.team.mapsport.adapter.EventsAdapter;
import com.team.mapsport.adapter.Participant;
import com.team.mapsport.adapter.User;
import com.team.mapsport.business.EventRepository;
import com.team.mapsport.business.ParticipantRepository;

import java.util.ArrayList;
import java.util.List;

public class EventList {

    static private ListView listViewEvent;
    static private EventsAdapter adapter;

    static private ParticipantRepository participantRepository;
    static private EventRepository eventRepository;


    public static void afficherEvents(View myFragmentView, FragmentActivity myActivityView, User user) {

        listViewEvent = (ListView) myFragmentView.findViewById(R.id.listViewEvent);

        participantRepository = new ParticipantRepository(myFragmentView.getContext());
        participantRepository.Open();
        List<Participant> participants = participantRepository.GetAllByAttribute(String.valueOf(user.getIdentifiant()), "user");

        eventRepository = new EventRepository(myFragmentView.getContext());
        eventRepository.Open();

        List<Event> events = new ArrayList<Event>();
        for (int i = 0; i < participants.size(); i++) {
            Event event = eventRepository.GetById((int) participants.get(i).getEvent());
            if (event != null) {
                events.add(event);
            }
        }
        adapter = new EventsAdapter(myFragmentView.getContext(), events,
                participants, (int) user.getIdentifiant(), myActivityView, myFragmentView);

        eventRepository.Close();
        participantRepository.Close();
        listViewEvent.setAdapter(adapter);
        myActivityView.registerForContextMenu(listViewEvent);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(myActivityView,
                android.R.layout.simple_spinner_item);

        dataAdapter.notifyDataSetChanged();

    }
}