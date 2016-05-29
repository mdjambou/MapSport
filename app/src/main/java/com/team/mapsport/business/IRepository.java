package com.team.mapsport.business;

import java.util.List;

import android.database.Cursor;

public interface IRepository<T>
{

    public List<T> GetAll();
    public T GetByMainAttribute(String attribut);
    public T GetById(int id);
    public T GetId(String arg1, String arg2, String arg3);
    public List<T> GetAllByAttribute(String attr, String typeAttr);
    
    public long  Save(T entite);
    public void Update(T entite);
    public void Delete(String id);
    
    public List<T> ConvertCursorToListObject(Cursor c);
    public T ConvertCursorToObject(Cursor c);
    public T ConvertCursorToOneObject(Cursor c);
}
