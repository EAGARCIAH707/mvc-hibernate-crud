package com.andevs.taller.mvc.controller;

import com.andevs.taller.mvc.model.dao.employee.EmployeeDAO;
import com.andevs.taller.mvc.model.dao.employee.IEmployeeDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class EmployeeController implements ActionListener {
    private final IEmployeeDAO employeeDAO;

    public EmployeeController() {
        employeeDAO = new EmployeeDAO();
    }

    private void create() {
        employeeDAO.create(getFormValues());
    }

    private void update() {
        employeeDAO.update(getFormValues());
    }

    private void delete() {
        employeeDAO.delete(1);
    }

    private Map<String, String> getFormValues() {
        Map<String, String> values = new HashMap<>();
        return values;
    }

    public void actionPerformed(ActionEvent e) {

    }
}
