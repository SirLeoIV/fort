package com.fort.model.dto;

public class ActionDTO {

    public int delay;

    public int increase; // if negative, decrease

    public long timestamp;

    public ActionDTO(int delay, int increase, long timestamp) {
        this.delay = delay;
        this.increase = increase;
        this.timestamp = timestamp;
    }
}
