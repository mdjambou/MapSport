package com.team.mapsport.business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.team.mapsport.adapter.Category;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends Repository<Category> {

    public CategoryRepository(Context context) {
        sqLiteOpenHelper = new DoItNowOpenHelper(context, null);
    }


    /**
     * Récupération de la liste de tous les produits
     */
    @Override
    public List<Category> GetAll() {
        // Récupération de la liste des courses
        String orderBy = DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME + " ASC";

        Cursor cursor = maBDD.query(DoItNowOpenHelper.CATEGORY_TABLE,
                new String[] { DoItNowOpenHelper.KEY_CATEGORY_COLUMN_ID,
                              DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME}, null, null, null,
                null, orderBy);

        return ConvertCursorToListObject(cursor);
    }

    /** TODO
     * Retourne un seul produit
     */
    @Override
    public Category GetByMainAttribute(String name) {
        String orderBy = DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME + " DESC";

        Cursor cursor = maBDD.query(DoItNowOpenHelper.CATEGORY_TABLE,
                new String[] { DoItNowOpenHelper.KEY_CATEGORY_COLUMN_ID,
                        DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME},
                DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME + "=?",
                new String[] { name }, null, null, orderBy);

        return ConvertCursorToObject(cursor);
    }

    // TODO
    @Override
    public Category GetId(String name, String arg2, String arg3) {
        String[] result_columns = new String[]{
                        DoItNowOpenHelper.KEY_CATEGORY_COLUMN_ID,
                        DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME};
        
        String where = DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME +"='" + name + "'";
        
        String orderBy = DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME+ " ASC";

        Cursor cursor = maBDD.query(DoItNowOpenHelper.CATEGORY_TABLE,result_columns,where,null,null,null,orderBy);

        return ConvertCursorToObject(cursor);
    }

    /**
     * Retourne un seul produit
     */
    @Override
    public Category GetById(int id) {
        Cursor cursor = maBDD.query(DoItNowOpenHelper.CATEGORY_TABLE,
                new String[] { DoItNowOpenHelper.KEY_CATEGORY_COLUMN_ID,
                        DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME,
                        },
                DoItNowOpenHelper.KEY_CATEGORY_COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null);

        return ConvertCursorToObject(cursor);
    }
    /**
     *  TODO
     * Enregistre en produit dans la base
     */
    @Override
    public long Save(Category entite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME, entite.getName());
        long id = maBDD.insert(DoItNowOpenHelper.CATEGORY_TABLE, null, contentValues);
        return id;
    }

    /**  TODO
     * Met à jour un produit
     */
    @Override
    public void Update(Category entite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME, entite.getName());
       
        maBDD.update(DoItNowOpenHelper.CATEGORY_TABLE, contentValues,
                        DoItNowOpenHelper.KEY_CATEGORY_COLUMN_ID + "=?",
                new String[] {String.valueOf(entite.getId())});
    }
    
    /**  TODO
     * Supprime un produit
     */
    @Override
    public void Delete(String name) {
        maBDD.delete(DoItNowOpenHelper.CATEGORY_TABLE,
                        DoItNowOpenHelper.KEY_CATEGORY_COLUMN_NAME + "=?",
                new String[] { name });
    }

    /**
     * Converti un curseur en une liste de produits
     */
    @Override
    public List<Category> ConvertCursorToListObject(Cursor c) {
        List<Category> liste = new ArrayList<>();

        if(c!=null){
                // Si la liste est vide
                if (c.getCount() == 0)
                    return liste;
        
                // position sur le preBeir item
                c.moveToFirst();
        
                // Pour chaque item
                int i = 0;
                while (i<c.getCount()){
                    Category category = new Category(c.getString(DoItNowOpenHelper.NUM_COLUMN_CATEGORY_NAME));
                    liste.add(category);
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
    public Category ConvertCursorToObject(Cursor c){

        Category category = null;
        if(c!=null){
           if(c.moveToFirst()){
                category = new Category(c.getString(DoItNowOpenHelper.NUM_COLUMN_CATEGORY_NAME));
               
                category.setId(c.getInt(DoItNowOpenHelper.NUM_COLUMN_CATEGORY_ID));
           }
        }

        return category;
    }

    /**  Not used Yet
     * Converti un curseur en un produit
     */
    @Override
    public Category ConvertCursorToOneObject(Cursor c) {
        c.moveToFirst();

        Category category = ConvertCursorToObject(c);

        c.close();
        return category;
    }

    // Not used Yet
    @Override
    public List<Category> GetAllByAttribute(String attr, String typeAttr)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
