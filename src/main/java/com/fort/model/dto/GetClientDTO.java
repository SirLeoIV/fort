package com.fort.model.dto;

public class GetClientDTO {

    public final String password;

    public final long id;

    public GetClientDTO(long id, String password) {
        this.id = id;
        this.password = password;
    }
}
