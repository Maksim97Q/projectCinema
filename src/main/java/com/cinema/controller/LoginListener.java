package com.cinema.controller;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.HashMap;
import java.util.Map;

//@WebListener
public class LoginListener implements HttpSessionAttributeListener {
    private static final Map<String, HttpSession> sessionMap = new HashMap<>();

    public static Map<String, HttpSession> getSessionMap() {
        return sessionMap;
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        sessionMap.put(session.getAttribute("name").toString(), session);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        sessionMap.remove(session.getAttribute("name").toString());
    }
}
