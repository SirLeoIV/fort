package com.fort.model.dto;

public class AddActionDTO {

    public final int delay;

    public final int increase; // if negative, decrease

    public final String clientName;

    public final String password;

    public AddActionDTO(int delay, int increase, String clientName, String password) {
        this.delay = delay;
        this.increase = increase;
        this.clientName = clientName;
        this.password = password;
    }
}
