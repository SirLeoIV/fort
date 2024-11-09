package com.fort.model.dto;

import java.util.List;

public class ClientDTO {

    private long id;

    private String password;

    private List<ActionDTO> actions;

    private int counter = 0;

    public ClientDTO(long id, String password, List<ActionDTO> actions) {
        this.id = id;
        this.password = password;
        this.actions = actions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ActionDTO> getActions() {
        return actions;
    }

    public void setActions(List<ActionDTO> actions) {
        this.actions = actions;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
