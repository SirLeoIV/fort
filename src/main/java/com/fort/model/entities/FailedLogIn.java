package com.fort.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FailedLogIn {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;

    public long timestamp;

    public String clientName;

    public FailedLogIn() {}

    public Long getId() {
        return id;
    }
}
