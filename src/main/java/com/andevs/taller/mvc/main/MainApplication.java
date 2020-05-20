package com.andevs.taller.mvc.main;

import com.andevs.taller.mvc.controller.LoginController;
import com.andevs.taller.mvc.view.login.LoginView;

public class MainApplication {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(loginView);
        loginView.addController(controller);
        loginView.setVisible(true);
        loginView.setLocationRelativeTo(null);
    }
}
