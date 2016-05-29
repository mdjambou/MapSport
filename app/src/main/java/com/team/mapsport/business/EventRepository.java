package com.team.mapsport.business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.team.mapsport.adapter.Event;

import java.text.ParseException;
import java.util.List;

public class EventRepository extends Repository<Event> {

    public EventRepository(Context context) {
        sqLiteOpenHelper = new DoItNowOpenHelper(context, null);
    }
    
    /**
     * Suppression d'un produit
     * 
     * @param id
     */
    public void DeleteEvent(int id) {
        maBDD.delete(DoItNowOpenHelper.EVENT_TABLE,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_ID + "=?",
                new String[] { String.valueOf(id) });
    }

    /**
     * Récupération de la liste de tous les produits
     */
    @Override
    public List<Event> GetAll() {
        // Récupération de la liste des courses
        String orderBy = DoItNowOpenHelper.KEY_EVENT_COLUMN_NAME + " DESC";

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
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_STATUS,
                }, null, null, null,
                null, orderBy);

        return ConvertCursorToListObject(cursor);
    }

    @Override
    public Event GetByMainAttribute(String id) {
        return null;
    }

    /**
     * Retourne un seul produit
     */
    @Override
    public Event GetById(int id) {
        String orderBy = DoItNowOpenHelper.KEY_EVENT_COLUMN_NAME + " DESC";

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

        return ConvertCursorToObject(cursor);
    }
    
    @Override
    public Event GetId(String name, String arg2, String arg3) {
        String[] result_columns = new String[]{
                DoItNowOpenHelper.KEY_EVENT_COLUMN_ID,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_NAME,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_CATEGORY,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_PICTURE,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_PRIVATE,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_SIZE,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_NB_PARTICIPANT,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_DESCRIPTION,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_LOCATION,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_STARTING_DATE,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_CLOSING_DATE,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_STATUS};
        
        String where = DoItNowOpenHelper.KEY_EVENT_COLUMN_NAME +" LIKE %'" + name + "'%";
        
        String orderBy = DoItNowOpenHelper.KEY_EVENT_COLUMN_ID + " DESC";

        Cursor cursor = maBDD.query(DoItNowOpenHelper.EVENT_TABLE, result_columns, where, null, null, null, orderBy);

        return ConvertCursorToObject(cursor);
    }

    /**
     * Enregistre en produit dans la base
     */
    @Override
    public long  Save(Event entite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_NAME, entite.getName());
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_CATEGORY, entite.getCategory());
        if(entite.isPrivateEvent()){
            contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_PRIVATE, 1);
        }else{
            contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_PRIVATE, 0);
        }
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_SIZE, entite.getSize());
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_NB_PARTICIPANT, entite.getNbParticipant());
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_DESCRIPTION, entite.getDescription());
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_LOCATION, entite.getLocation());
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_STARTING_DATE, entite.getStartingDate());
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_CLOSING_DATE, entite.getClosingDate());
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_STATUS, entite.getStatus());

        long id = maBDD.insert(DoItNowOpenHelper.EVENT_TABLE, null, contentValues);
        return id;
    }

    /**
     * Met à jour un produit
     */
    @Override
    public void Update(Event entite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoItNowOpenHelper.KEY_EVENT_COLUMN_NAME, entite.getName());
       
        maBDD.update(DoItNowOpenHelper.EVENT_TABLE, contentValues,
                DoItNowOpenHelper.KEY_EVENT_COLUMN_ID + "=?",
                new String[]{String.valueOf(entite.getId())});
    }
    
    /**
     * Supprime un produit
     */
    @Override
    public void Delete(String id) {
        maBDD.delete(DoItNowOpenHelper.EVENT_TABLE,
                        DoItNowOpenHelper.KEY_EVENT_COLUMN_ID + "=?",
                new String[] { id });
    }

    @Override
    public List<Event> ConvertCursorToListObject(Cursor c) {
        return null;
    }


    /**
     * Converti un curseur en une liste de produits
     */
    /* @Override
    public List<Event> ConvertCursorToListObject(Cursor c) {
        List<Event> liste = new ArrayList<>();

        if(c!=null){
                // Si la liste est vide
                if (c.getCount() == 0)
                    return liste;
        
                // position sur le premeir item
                c.moveToFirst();
        
                // Pour chaque item
                int i = 0;
               while (i<c.getCount()){
                    Event event = new Event(
                            c.getString(DoItNowOpenHelper.NUM_COLUMN_EVENT_NAME),
                            c.getInt(DoItNowOpenHelper.NUM_COLUMN_EVENT_CATEGORY),
                            c.getInt(DoItNowOpenHelper.NUM_COLUMN_EVENT_PRIVACY),
                            c.getInt(DoItNowOpenHelper.NUM_COLUMN_EVENT_SIZE),
                            c.getString(DoItNowOpenHelper.NUM_COLUMN_EVENT_DESCRIPTION),
                            c.getString(DoItNowOpenHelper.NUM_COLUMN_EVENT_LOCATION),
                            c.getString(DoItNowOpenHelper.NUM_COLUMN_EVENT_STARTING_DATE),
                            c.getString(DoItNowOpenHelper.NUM_COLUMN_EVENT_CLOSING_DATE),
                            c.getInt(DoItNowOpenHelper.NUM_COLUMN_EVENT_STATUS)
                    );
                    liste.add(event);
                    i++;
                    c.moveToNext();
                }
         }

        // Fermeture du curseur
        c.close();

        return liste;
    }*/

    /**
     * Méthode utilisée par ConvertCursorToObject et ConvertCursorToListObject
     * @throws ParseException 
     */
    @Override
    public Event ConvertCursorToObject(Cursor c){

        Event event = null;
        if(c!=null){
          if(c.moveToFirst()){
              int id = c.getInt(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_ID));
              String name = c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_NAME));
              int catID = c.getInt(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_CATEGORY));
              boolean privateEvent = false;
              int privacy = c.getInt(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_PRIVATE));
              if(privacy == 1){
                  privateEvent =true;
              }

              int size = c.getInt(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_SIZE));
              int nbPart = c.getInt(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_NB_PARTICIPANT));
              String descr = c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_DESCRIPTION));
              String location = c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_DESCRIPTION));
              String sDate = c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_STARTING_DATE));
              String cDate = c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_CLOSING_DATE));
              int status = c.getInt(c.getColumnIndex(DoItNowOpenHelper.KEY_EVENT_COLUMN_STATUS));
              event = new Event(name,catID,privateEvent,size,descr,location,sDate,cDate);
              event.setId(id);
              event.setStatus(status);
              event.setNbParticipant(nbPart);

          }
        }

        return event;
    }

    /**
     * Converti un curseur en un produit
     */
    @Override
    public Event ConvertCursorToOneObject(Cursor c) {
        c.moveToFirst();

        Event event = ConvertCursorToObject(c);

        c.close();
        return event;
    }

    @Override
    public List<Event> GetAllByAttribute(String attr, String typeAttr)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
