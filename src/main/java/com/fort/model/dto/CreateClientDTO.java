package com.fort.model.dto;

public class CreateClientDTO {

    public String password;

    public CreateClientDTO() {}

    public CreateClientDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
