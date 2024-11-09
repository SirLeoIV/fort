package com.fort.controller;


import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("Session Created:: ID=" + event.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Session Destroyed:: ID=" + event.getSession());
    }
}