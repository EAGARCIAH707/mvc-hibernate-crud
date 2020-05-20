package com.andevs.taller.mvc.controller;

import com.andevs.taller.mvc.model.dao.employee.EmployeeDAO;
import com.andevs.taller.mvc.model.dao.employee.IEmployeeDAO;
import com.andevs.taller.mvc.model.entities.Employee;
import com.andevs.taller.mvc.view.employee.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class EmployeeController implements ActionListener {
    private final IEmployeeDAO employeeDAO;
    private final EmployeeView employeeView;

    public EmployeeController(EmployeeView employeeView) {
        this.employeeView = employeeView;
        employeeDAO = new EmployeeDAO();
    }

    private void create() {
        Boolean result = employeeDAO.create(getFormValues());
        if (result) {
            JOptionPane.showMessageDialog(null, "Registro creado con exito");
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible crear",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void update() {
        Boolean result = employeeDAO.update(getFormValues());
        if (result) {
            JOptionPane.showMessageDialog(null, "Registro actualizado con exito");

        } else {
            JOptionPane.showMessageDialog(null, "No fue posible actualizar",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void delete() {
        employeeDAO.delete(1);
    }

    private void read() {
        try {
            Long docNumber = Long.parseLong(employeeView.getDocNumberField().getText());
            Employee employee = employeeDAO.findByDocNumber(docNumber);
            if (employee.getEmployeeId() != null) {
                writeDataInForm(employee);
            }
        } catch (Exception e) {
            System.out.println("Error in read ".concat(e.getMessage()));
        }
    }

    private void readAll() {
    }

    private Map<String, String> getFormValues() {
        Map<String, String> values = new HashMap<>();
        values.put("employeeId", employeeView.getIdLabel().getText());
        values.put("documentNumber", employeeView.getDocNumberField().getText());
        values.put("name", employeeView.getNameField().getText());
        values.put("surname", employeeView.getSurnameField().getText());
        values.put("secondSurname", employeeView.getSurname2Field().getText());
        values.put("phone", employeeView.getPhoneField().getText());
        return values;
    }

    private void writeDataInForm(Employee employee) {
        if (employee.getEmployeeId() != null) {
            employeeView.getIdLabel().setText(employee.getEmployeeId().toString());
        }
        employeeView.getDocNumberField().setText(employee.getDocumentNumber().toString());
        employeeView.getNameField().setText(employee.getName());
        employeeView.getSurnameField().setText(employee.getSurname());
        employeeView.getSurname2Field().setText(employee.getSecondSurname());
        employeeView.getPhoneField().setText(employee.getPhone());
        employeeView.getCreatedOnField().setText(employee.getCretaedOn().toString());
        employeeView.getLastModifiedField().setText(employee.getLastModified().toString());
    }

    private void cleanForm() {
        employeeView.getIdLabel().setText("");
        employeeView.getDocNumberLabel().setText("");
        employeeView.getNameLabel().setText("");
        employeeView.getSurnameLabel().setText("");
        employeeView.getSurname2Label().setText("");
        employeeView.getPhoneLabel().setText("");
        employeeView.getCreatedOnField().setText("");
        employeeView.getLastModifiedLabel().setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(employeeView.getCreateButton())) {
            create();
        }
        if (e.getSource().equals(employeeView.getUpdateButton())) {
            update();
        }
        if (e.getSource().equals(employeeView.getDeleteButton())) {
            delete();
        }
        if (e.getSource().equals(employeeView.getReadButton())) {
            read();
        }
        if (e.getSource().equals(employeeView.getReadAllButton())) {
            readAll();
        }
        if (e.getSource().equals(employeeView.getCleanButton())) {
            cleanForm();
        }
    }
}
