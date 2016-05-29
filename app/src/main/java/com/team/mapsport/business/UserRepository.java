package com.team.mapsport.business;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.team.mapsport.adapter.User;

public class UserRepository extends Repository<User> {
    
    public UserRepository(Context context) {
        sqLiteOpenHelper = new DoItNowOpenHelper(context, null);
    }

    /**
     * Suppression d'un produit
     * 
     * @param email
     */
    /*public void DeleteUser(String email) {
        maBDD.delete(DoItNowOpenHelper.USER_TABLE,
                        DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL + "=?",
                new String[] { String.valueOf(email) });
    }*/

    /** Not used yet
     * Récupération de la liste de tous les produits
     */
    @Override
    public List<User> GetAll() {
        // Recuperation de la liste des utilisateurs
        Cursor cursor = maBDD.query(DoItNowOpenHelper.USER_TABLE,
                new String[] {
                        DoItNowOpenHelper.KEY_USER_COLUMN_FIRST_NAME,
                        DoItNowOpenHelper.KEY_USER_COLUMN_LAST_NAME,
                        DoItNowOpenHelper.KEY_USER_COLUMN_PSEUDO,
                        DoItNowOpenHelper.KEY_USER_COLUMN_PASSWORD,
                        DoItNowOpenHelper.KEY_USER_COLUMN_PICTURE,
                        DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL,
                        DoItNowOpenHelper.KEY_USER_COLUMN_CITY,
                        DoItNowOpenHelper.KEY_USER_COLUMN_COUNTRY,
                        DoItNowOpenHelper.KEY_USER_COLUMN_CELLNUMBER}, null, null, null,
                null, null);

        return ConvertCursorToListObject(cursor);
    }

    /** Not used yet
     * Retourne un seul produit
     */
   @Override
    public User GetById(int id) {
        Cursor cursor = maBDD.query(DoItNowOpenHelper.USER_TABLE,
                new String[] { DoItNowOpenHelper.KEY_USER_COLUMN_FIRST_NAME,
                        DoItNowOpenHelper.KEY_USER_COLUMN_LAST_NAME,
                        DoItNowOpenHelper.KEY_USER_COLUMN_PSEUDO,
                        DoItNowOpenHelper.KEY_USER_COLUMN_PASSWORD,
                        DoItNowOpenHelper.KEY_USER_COLUMN_PICTURE,
                        DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL,
                        DoItNowOpenHelper.KEY_USER_COLUMN_CITY,
                        DoItNowOpenHelper.KEY_USER_COLUMN_COUNTRY,
                        DoItNowOpenHelper.KEY_USER_COLUMN_CELLNUMBER},
                DoItNowOpenHelper.KEY_USER_COLUMN_IDENTIFIANT + "=?",
                new String[] { String.valueOf(id) }, null, null, null);

        return ConvertCursorToObject(cursor);
    }
    /**
     * Retourne un seul produit
     */
    @Override
    public User GetByMainAttribute(String email) {
       String[] result_columns = new String[]{
               DoItNowOpenHelper.KEY_USER_COLUMN_IDENTIFIANT,
               DoItNowOpenHelper.KEY_USER_COLUMN_FIRST_NAME,
                        DoItNowOpenHelper.KEY_USER_COLUMN_LAST_NAME,
                        DoItNowOpenHelper.KEY_USER_COLUMN_PSEUDO,
                        DoItNowOpenHelper.KEY_USER_COLUMN_PASSWORD,
                        DoItNowOpenHelper.KEY_USER_COLUMN_PICTURE,
                        DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL,
                        DoItNowOpenHelper.KEY_USER_COLUMN_CELLNUMBER,
                        DoItNowOpenHelper.KEY_USER_COLUMN_CITY,
                        DoItNowOpenHelper.KEY_USER_COLUMN_COUNTRY
                };

       String where= DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL + "='" + email + "'";


        Cursor cursor = maBDD.query(DoItNowOpenHelper.USER_TABLE,result_columns,where,null,null,null,null);

        return ConvertCursorToObject(cursor);
    }


   @Override
    public User GetId(String pseudo, String password,String email)
    {
        String[] result_columns = new String[]{
                DoItNowOpenHelper.KEY_USER_COLUMN_FIRST_NAME,
                DoItNowOpenHelper.KEY_USER_COLUMN_LAST_NAME,
                DoItNowOpenHelper.KEY_USER_COLUMN_PSEUDO,
                DoItNowOpenHelper.KEY_USER_COLUMN_PASSWORD,
                DoItNowOpenHelper.KEY_USER_COLUMN_PICTURE,
                DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL,
                DoItNowOpenHelper.KEY_USER_COLUMN_CITY,
                DoItNowOpenHelper.KEY_USER_COLUMN_COUNTRY,
                DoItNowOpenHelper.KEY_USER_COLUMN_CELLNUMBER
        };
        String where="";
        if(pseudo != null && email!= null) {
            where = DoItNowOpenHelper.KEY_USER_COLUMN_PSEUDO + "='" + pseudo + "' OR "
                    + DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL + "='" + email + "'";
        }
        
        Cursor cursor = maBDD.query(DoItNowOpenHelper.USER_TABLE,result_columns,where,null,null,null,null);

        return ConvertCursorToObject(cursor);
    }

    /**
     * Enregistre en produit dans la base
     */
    @Override
    public long Save(User entite) {
        ContentValues contentValues = new ContentValues();
        //contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_CELLNUMBER, entite.getCellNumber());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_PSEUDO, entite.getPseudo());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_PASSWORD, entite.getPassword());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_FIRST_NAME, entite.getFirstName());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_LAST_NAME, entite.getLastName());
        /*contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_CITY, entite.getCity());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_COUNTRY, entite.getCountry());*/
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL, entite.getEmail());

        long id = maBDD.insert(DoItNowOpenHelper.USER_TABLE, null, contentValues);
        return id;
    }

    /**  Not used yet
     * Met à jour un produit
     */
    @Override
    public void Update(User entite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_CELLNUMBER, entite.getCellNumber());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_PSEUDO, entite.getPseudo());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_PASSWORD, entite.getPassword());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_FIRST_NAME, entite.getFirstName());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_LAST_NAME, entite.getLastName());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_CITY, entite.getCity());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_COUNTRY, entite.getCountry());
        contentValues.put(DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL, entite.getEmail());

        maBDD.update(DoItNowOpenHelper.USER_TABLE, contentValues,
                        DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL + "=?",
                new String[] { entite.getEmail() });
    }

    /**  Not used yet
     * Supprime un produit
     */
    @Override
    public void Delete(String email) {
        maBDD.delete(DoItNowOpenHelper.USER_TABLE,
                        DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL + "=?",
                new String[] { email });
    }

    /** Not used yet
     * Converti un curseur en une liste de produits
     */
    @Override
    public List<User> ConvertCursorToListObject(Cursor c) {

        List<User> liste = new ArrayList<>();

        // Si la liste est vide
        if (c.getCount() == 0)
            return liste;

        // position sur le premeir item
        c.moveToFirst();

        // Pour chaque item
        int i = 0;
        while (i<c.getCount()){

            User user = new User(
                            c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_PSEUDO)),
                            c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_PASSWORD)),
                            c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL)));
            user.setFirstName(c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_FIRST_NAME)));
            user.setLastName(c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_LAST_NAME)));
           /* user.setCellNumber(c.getInt(DoItNowOpenHelper.NUM_COLUMN_CELLNUMBER));
            user.setCity(c.getString(DoItNowOpenHelper.NUM_COLUMN_CITY));
            user.setCountry(c.getString(DoItNowOpenHelper.NUM_COLUMN_COUNTRY));*/

            user.setIdentifiant(c.getInt(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_IDENTIFIANT)));

            liste.add(user);
            i++;
            c.moveToNext();
        }

        // Fermeture du curseur
        c.close();

        return liste;
    }

    /**
     * Méthode utilisée par ConvertCursorToObject et ConvertCursorToListObject
     */
    @Override
    public User ConvertCursorToObject(Cursor c) {
        User user = null;


           if(c.moveToFirst()){
               user = new User(
                       c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_PSEUDO)),
                       c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_PASSWORD)),
                       c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_EMAIL)));
               user.setFirstName(c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_FIRST_NAME)));
               user.setLastName(c.getString(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_LAST_NAME)));
           /* user.setCellNumber(c.getInt(DoItNowOpenHelper.NUM_COLUMN_CELLNUMBER));
            user.setCity(c.getString(DoItNowOpenHelper.NUM_COLUMN_CITY));
            user.setCountry(c.getString(DoItNowOpenHelper.NUM_COLUMN_COUNTRY));*/

               user.setIdentifiant(c.getInt(c.getColumnIndex(DoItNowOpenHelper.KEY_USER_COLUMN_IDENTIFIANT)));

           }


        return user;
    }

    /** Not used yet
     * Converti un curseur en un produit
     */
    @Override
    public User ConvertCursorToOneObject(Cursor c) {
       c.moveToFirst();

        User user = ConvertCursorToObject(c);

        c.close();
        return user;
    }

    //Not used yet
    @Override
    public List<User> GetAllByAttribute(String attr, String typeAttr)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
