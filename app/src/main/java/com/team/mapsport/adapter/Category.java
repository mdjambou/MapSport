package com.team.mapsport.adapter;


public class Category
{
    private String name;
    private long id;

    public Category(String name) {
        this.name = name;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
