package com.team.mapsport.adapter;

import com.team.mapsport.common.Constants;

public class Participant
{
    private long id;
    private long user;
    private long event;
    private String type; // host,guest
    private String answer;// O = no, 1 = yes, 2 = may be

    public Participant(long user, long event,String type, String answer ) {
        this.user = user;
        this.event = event;
        this.type = type;
        this.answer = answer;
    }
    public Participant(long user, long event) {
        this.user = user;
        this.event = event;
        this.type = Constants.HOSTEVENT;
        this.answer = Constants.ANSWEREVENT_YES;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getEvent() {
        return event;
    }

    public void setEvent(long event) {
        this.event = event;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
