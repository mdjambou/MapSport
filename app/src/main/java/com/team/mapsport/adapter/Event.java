package com.team.mapsport.adapter;


public class Event
{
    private long id;
    private String name;
    private long category;
    private boolean privateEvent;
    private int size;
    private int nbParticipant;
    private String description;
    private String location;
    private String startingDate;
    private String closingDate;
    private int status; //0=Open; 1=Happening;2=Cancel; 3=Close

    public Event(String name, long category, boolean privateEvent,int size, String descript, String location, String sDate, String cDate) {
        this.name = name;
        this.category = category;
        this.privateEvent = privateEvent;
        this.size=size;
        this.nbParticipant = 1;
        this.description = descript;
        this.location = location;
        this.startingDate = sDate;
        this.closingDate=cDate;
        this.status = 0;
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

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public boolean isPrivateEvent() {
        return privateEvent;
    }

    public void setPrivateEvent(boolean privateEvent) {
        this.privateEvent = privateEvent;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNbParticipant() {
        return nbParticipant;
    }

    public void setNbParticipant(int nbParticipant) {
        this.nbParticipant = nbParticipant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
