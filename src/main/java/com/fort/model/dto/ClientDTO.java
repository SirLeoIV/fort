package com.fort.model.dto;

import java.util.List;

public class ClientDTO {

    private long id;

    private String password;

    private List<ActionDTO> actions;

    private ServerDTO server;

    private int counter = 0;

    public ClientDTO(long id, String password, List<ActionDTO> actions, ServerDTO server) {
        this.id = id;
        this.password = password;
        this.actions = actions;
        this.server = server;
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

    public ServerDTO getServer() {
        return server;
    }

    public void setServer(ServerDTO server) {
        this.server = server;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
