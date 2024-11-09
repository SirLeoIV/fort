package com.fort.model.dto;

public class AddActionDTO {

    public final int delay;

    public final int increase; // if negative, decrease

    public final long clientID;

    public final String password;


    public AddActionDTO(int delay, int increase, long clientID, String password) {
        this.delay = delay;
        this.increase = increase;
        this.clientID = clientID;
        this.password = password;
    }
}
