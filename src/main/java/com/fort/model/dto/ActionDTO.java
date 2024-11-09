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

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
