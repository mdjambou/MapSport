package com.team.mapsport.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import com.team.mapsport.R;
import com.team.mapsport.business.CategoryRepository;
import com.team.mapsport.common.Constants;

import java.util.List;


public class EventsAdapter extends BaseAdapter
{
    private List<Participant> listParticipant;
    private List<Event> listEvent;
    private LayoutInflater inflater;
    private Context context;
    int userID;
    private FragmentActivity parentActivityView;
    private View parentFragmentView;

    public void setAlertes(List<Event> listeEvents) {
        this.listEvent = listeEvents;
    }

    public EventsAdapter(Context context,List<Event> events, List<Participant> participants,
                               int userID,FragmentActivity myActivityView,View myFragmentView) {
        this.listEvent = events;
        this.listParticipant = participants;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.userID = userID;
        this.parentActivityView = myActivityView;
        this.parentFragmentView =  myFragmentView;
    }

    @Override
    public int getCount() {
        return listEvent.size();
    }

    @Override
    public Object getItem(int position) {
        return listEvent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listEvent.get(position).getId();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        holder = new ViewHolder();
        view = inflater.inflate(R.layout.layout_item_events, null);

        CategoryRepository categoryRepository = new CategoryRepository(context);
        categoryRepository.Open();
        Category cat = categoryRepository.GetById((int)listEvent.get(position).getCategory());
        categoryRepository.Close();

       /* holder.category = (TextView) view.findViewById(R.id.category);
        holder.category.setText(cat.getName());
    */
        Event ev = listEvent.get(position);

        holder.name = (TextView) view.findViewById(R.id.nameEvent);

        String nameEvent = listEvent.get(position).getName();
        /*if(nameEvent.length()> 13){
            nameEvent = nameEvent.substring(0,10)+" ...";
        }else{
            for(int i = nameEvent.length(); i<=13;i++){
               nameEvent =  nameEvent +" ";
            }
        }*/

        holder.name.setText(nameEvent);
        holder.details = (TextView) view.findViewById(R.id.detailsEvent);

        int nbPart = listEvent.get(position).getNbParticipant();
        int size = listEvent.get(position).getSize();

        /*holder.participantSize = (TextView) view.findViewById(R.id.participantSize);
        holder.participantSize.setText(nbPart+ " / "+size +" participants");*/

        String sizeEv = nbPart+ " / "+size +" participants";
        String start = listEvent.get(position).getStartingDate();
        String dateHeure [] = start.split("à");
        //String city = listEvent.get(position).getLocation();
       /* holder.dHCity = (TextView) view.findViewById(R.id.dateCity);
        holder.dHCity.setText(dateHeure[0] + " à Tours");*/
        String dhCity = dateHeure[0] + " à Tours";

        String descript = listEvent.get(position).getDescription();
        if(descript.length()>= 22){
            descript = descript.substring(0,22)+" ...";
        }else{
            for(int i = nameEvent.length(); i<=22;i++){
                descript =  descript +" ";
            }
            descript= descript+" ...";
        }
        holder.details.setText(descript +"\n"+sizeEv +"\n"+dhCity+"\n"+cat.getName());

        holder.host = (TextView) view.findViewById(R.id.host);
        String hostOrGuest = "";
        if(listParticipant.get(position).getType().equals(Constants.HOSTEVENT)){
            hostOrGuest = " Vous êtes l'hôte";
            holder.host.setTextColor(Color.parseColor("#F5920F"));
        }else{
            holder.host.setTextColor(Color.parseColor("#F5920F"));
            if(listParticipant.get(position).getAnswer().equals(Constants.ANSWEREVENT_YES)){
                hostOrGuest = " Vous participez à cet évènement";
            }else{
                holder.host.setTextColor(Color.RED);
                hostOrGuest = " Vous êtes intéressé par cet évènement";
            }
        }
        holder.host.setText(hostOrGuest);

        holder.more = (TableRow) view.findViewById(R.id.moreInfos);
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DeleteLeisure.removeActivities(parentFragmentView, parentActivityView, holder.actButton);
            }
        });

        view.setTag(holder);


        return view;
    }

    private class ViewHolder {
        public TextView name, details, participantSize,dHCity, host;
        public TableRow more;


    }



}
