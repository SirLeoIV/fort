package com.fort.model.dto;

public class GetClientDTO {

    public final String name;

    public final String password;

    public GetClientDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
