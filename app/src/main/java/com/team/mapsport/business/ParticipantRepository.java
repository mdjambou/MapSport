package com.team.mapsport.business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.team.mapsport.adapter.Participant;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantRepository extends Repository<Participant> {

    public ParticipantRepository(Context context) {
        sqLiteOpenHelper = new DoItNowOpenHelper(context, null);
    }


    /** Not used yet
     * Récupération de la liste de tous les produits
     */
    @Override
    public List<Participant> GetAll() {
        // Récupération de la liste des courses
        /*String orderBy = DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT + " ASC";

        Cursor cursor = maBDD.query(DoItNowOpenHelper.PARTICIPANT_TABLE,
                new String[] { DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ID,
                        DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_USER,
                        DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT,
                        DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_TYPE,
                        DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ANSWER
                }, null, null, null,
                null, orderBy);

        return ConvertCursorToListObject(cursor);*/
        return null;
    }

    @Override
    public Participant GetByMainAttribute(String id) {
        return null;
    }

    /** TODO
     * Not used yet
     * Retourne un seul produit
     */
    @Override
    public Participant GetById(int id) {
        /*String orderBy = DoItNowOpenHelper.KEY_EVENT_COLUMN_NAME + " DESC";

        Cursor cursor = maBDD.query(DoItNowOpenHelper.EVENT_TABLE,
                new String[] { DoItNowOpenHelper.KEY_EVENT_COLUMN_ID,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_NAME,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_CATEGORY,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_PRIVATE,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_SIZE,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_NB_PARTICIPANT,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_DESCRIPTION,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_LOCATION,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_STARTING_DATE,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_CLOSING_DATE,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_STATUS},
                DoItNowOpenHelper.KEY_EVENT_COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, orderBy);

        return ConvertCursorToObject(cursor);*/
        return null;
    }

    @Override
    public Participant GetId(String userID, String eventID, String arg3) {
        String[] result_columns = new String[]{
                DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ID,
                DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_USER,
                DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT,
                DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ANSWER,
                DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_TYPE,
               };

        String where ="";
        if(userID!=null && eventID!=null){
            where = DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_USER + "=" + Integer.parseInt(userID) +
                    " AND "+ DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT + "=" + Integer.parseInt(eventID);

        }
        String orderBy = DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT + " DESC";

        Cursor cursor = maBDD.query(DoItNowOpenHelper.PARTICIPANT_TABLE, result_columns, where, null, null, null, orderBy);

        return ConvertCursorToObject(cursor);
    }

    @Override
    public List<Participant> GetAllByAttribute(String attr, String typeAttr)
    {
        String[] result_columns = new String[]{
                DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ID,
                DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_USER,
                DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT,
                DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ANSWER,
                DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_TYPE,
        };

        String where ="";
        if(typeAttr.equals("user")){
            where = DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_USER + "=" + Integer.parseInt(attr);

        }else if(typeAttr.equals("event")){
            where = DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT + "=" + Integer.parseInt(attr);
        }

        String orderBy = DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT + " DESC";

        Cursor cursor = maBDD.query(DoItNowOpenHelper.PARTICIPANT_TABLE, result_columns, where, null, null, null, orderBy);

        return ConvertCursorToListObject(cursor);
    }
    /**
     * Enregistre en produit dans la base
     */
    @Override
    public long  Save(Participant entite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT, entite.getEvent());
        contentValues.put(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_USER, entite.getUser());
        contentValues.put(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_TYPE, entite.getType());
        contentValues.put(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ANSWER, entite.getAnswer());

        long id = maBDD.insert(DoItNowOpenHelper.PARTICIPANT_TABLE, null, contentValues);
        return id;
    }



    /** TODO
     * Met à jour un produit
     */
    @Override
    public void Update(Participant entite) {
        /*ContentValues contentValues = new ContentValues();
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_NAME, entite.getName());
       
        maBDD.update(DoItNowOpenHelper.EVENT_TABLE, contentValues,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_ID + "=?",
                new String[] {String.valueOf(entite.getId())});*/
    }
    
    /** TODO
     * Supprime un produit
     */
    @Override
    public void Delete(String id) {
      /*  maBDD.delete(DoItNowOpenHelper.EVENT_TABLE,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_ID + "=?",
                new String[] { id });*/
    }

    /**
     * Converti un curseur en une liste de produits
     */
     @Override
    public List<Participant> ConvertCursorToListObject(Cursor c) {
       List<Participant> liste = new ArrayList<>();

        if(c!=null){
                // Si la liste est vide
                if (c.getCount() == 0)
                    return liste;
        
                // position sur le premeir item
                c.moveToFirst();
        
                // Pour chaque item
                int i = 0;
               while (i<c.getCount()){
                   Participant participant = new Participant(c.getLong(c.getColumnIndex(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_USER)),
                           c.getLong(c.getColumnIndex(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT)),
                           c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_TYPE)),
                           c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ANSWER)));

                   participant.setId(c.getInt(c.getColumnIndex(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ID)));
                      liste.add(participant);
                    i++;
                    c.moveToNext();
                }
         }

        // Fermeture du curseur
        c.close();

        return liste;
    }

    /**
     * Méthode utilisée par ConvertCursorToObject et ConvertCursorToListObject
     * @throws ParseException 
     */

    @Override
    public Participant ConvertCursorToObject(Cursor c){

        Participant participant = null;
        if(c!=null){
          if(c.moveToFirst()){
              participant = new Participant(c.getLong(c.getColumnIndex(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_USER)),
                       c.getLong(c.getColumnIndex(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_EVENT)),
                      c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_TYPE)),
                       c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ANSWER)));

              participant.setId(c.getInt(c.getColumnIndex(DoItNowOpenHelper.KEY_PARTICIPANT_COLUMN_ID)));
           }
        }

        return participant;
    }

    /**
     * Converti un curseur en un produit
     */
    @Override
    public Participant ConvertCursorToOneObject(Cursor c) {
        /*c.moveToFirst();

        Event event = ConvertCursorToObject(c);

        c.close();
        return event;*/
        return null;
    }

}
