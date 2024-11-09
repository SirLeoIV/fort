package com.fort.model.entities;

import jakarta.persistence.*;

@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public long timestamp;

    public int delay;

    @ManyToOne
    public Client client;

    public int increase; // if negative, decrease

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
