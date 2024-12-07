package com.fort.model.dto;

import java.util.List;

public class ClientDTO {

    private String name;

    private String password;

    private List<ActionDTO> actions;

    private int counter = 0;

    public ClientDTO(String name, String password, List<ActionDTO> actions) {
        this.name = name;
        this.password = password;
        this.actions = actions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
