package com.andevs.taller.mvc.controller;

import com.andevs.taller.mvc.model.dao.login.LoginDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private final LoginDAO loginDAO;

    public LoginController() {
        loginDAO = new LoginDAO();
    }

    public void actionPerformed(ActionEvent e) {

    }
}
