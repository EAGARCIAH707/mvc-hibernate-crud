package com.andevs.taller.mvc.controller;

import com.andevs.taller.mvc.model.dao.login.LoginDAO;
import com.andevs.taller.mvc.model.util.log.LogUtil;
import com.andevs.taller.mvc.view.employee.EmployeeView;
import com.andevs.taller.mvc.view.login.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private final LoginView loginView;
    private final LoginDAO loginDAO;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginDAO = new LoginDAO();
    }

    private void login() {
        String user = loginView.getFieldUser().getText().trim();
        String pass = loginView.getFieldPass().getText().trim();
        Boolean result = loginDAO.login(user, pass);
        if (!result) {
            JOptionPane.showMessageDialog(null, "Datos Invalidos",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
        } else {
            EmployeeView employeeView = new EmployeeView();
            EmployeeController controller = new EmployeeController(employeeView);
            loginView.dispose();
            employeeView.addController(controller);
            employeeView.setVisible(true);
            employeeView.setLocationRelativeTo(null);
        }
    }

    public void showPass() {
        if (loginView.getShowPass().isSelected()) {
            loginView.getFieldPass().setEchoChar((char) 0);

        } else {
            loginView.getFieldPass().setEchoChar('*');
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == loginView.getLogin()) {
                login();
            }
            if (e.getSource() == loginView.getShowPass()) {
                showPass();
            }
        } catch (Exception ex) {
            LogUtil.writeInLog("Error in loginController " + ex.getMessage());
        }
    }
}
