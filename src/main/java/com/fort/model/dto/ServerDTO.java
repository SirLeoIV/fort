package com.fort.model.dto;

public class ServerDTO {

    private String ip;
    private int port;

    public ServerDTO(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public ServerDTO() {
        this.ip = "localhost";
        this.port = 8080;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
