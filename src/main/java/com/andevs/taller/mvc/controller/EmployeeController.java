package com.andevs.taller.mvc.controller;

import com.andevs.taller.mvc.model.dao.employee.EmployeeDAO;
import com.andevs.taller.mvc.model.dao.employee.IEmployeeDAO;
import com.andevs.taller.mvc.model.entities.Employee;
import com.andevs.taller.mvc.model.util.log.LogUtil;
import com.andevs.taller.mvc.view.employee.EmployeeView;
import com.andevs.taller.mvc.view.log.LogView;
import com.andevs.taller.mvc.view.table.EmployeeTableView;

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
            cleanAfterExecute();
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible crear",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void update() {
        try {
            Integer.parseInt(employeeView.getIdLabel().getText());
            Boolean result = employeeDAO.update(getFormValues());
            if (result) {
                JOptionPane.showMessageDialog(null, "Registro actualizado con exito");
                cleanAfterExecute();

            } else {
                JOptionPane.showMessageDialog(null, "No fue posible actualizar",
                        "Alerta", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException e) {
            String message = "Para actualizar es necesario consultar primero\n " +
                    "Esto con el fin de garantizar informacion reciente";
            JOptionPane.showMessageDialog(null, message, "Alerta",
                    JOptionPane.WARNING_MESSAGE);
            LogUtil.writeInLog("find the employee first before updating");

        } catch (Exception e) {
            LogUtil.writeInLog("Error in update() ".concat(e.getMessage()));
        }


    }

    private void delete() {
        try {
            Integer id = Integer.parseInt(employeeView.getIdLabel().getText());
            Boolean result = employeeDAO.delete(id);
            if (result) {
                JOptionPane.showMessageDialog(null, "Registro eliminado con exito");
                cleanAfterExecute();

            } else {
                JOptionPane.showMessageDialog(null, "No fue posible eliminar",
                        "Alerta", JOptionPane.WARNING_MESSAGE);
                LogUtil.writeInLog("Not is possible delete ");
            }
        } catch (NumberFormatException e) {
            String message = "Para eliminar es necesario consultar primero\n " +
                    "Esto con el fin de garantizar informacion reciente";
            JOptionPane.showMessageDialog(null, message, "Alerta",
                    JOptionPane.WARNING_MESSAGE);
            LogUtil.writeInLog("look for the employee first before removing");

        } catch (Exception e) {
            LogUtil.writeInLog("Error in delete() ".concat(e.getMessage()));
        }

    }

    private void read() {
        try {
            Long docNumber = Long.parseLong(employeeView.getDocNumberField().getText());
            Employee employee = employeeDAO.findByDocNumber(docNumber);
            if (employee.getEmployeeId() != null) {
                writeDataInForm(employee);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No fue posible consultar",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            LogUtil.writeInLog("Not is possible find ");
        }
    }

    private void readAll() {
        EmployeeTableView employeeTableView = new EmployeeTableView();
        TableEmployeeController controller = new TableEmployeeController(employeeTableView);
        employeeTableView.addController(controller);
        employeeTableView.setVisible(true);
        employeeTableView.setLocationRelativeTo(null);
    }

    private Map<String, String> getFormValues() {
        Map<String, String> values = new HashMap<>();
        values.put("employeeId", employeeView.getIdLabel().getText());
        values.put("documentNumber", employeeView.getDocNumberField().getText());
        values.put("name", employeeView.getNameField().getText());
        values.put("surname", employeeView.getSurnameField().getText());
        values.put("secondSurname", employeeView.getSurname2Field().getText());
        values.put("phone", employeeView.getPhoneField().getText());
        values.put("cretaedOn", employeeView.getCreatedOnField().getText());
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
        employeeView.getDocNumberField().setText("");
        employeeView.getNameField().setText("");
        employeeView.getSurnameField().setText("");
        employeeView.getSurname2Field().setText("");
        employeeView.getPhoneField().setText("");
        employeeView.getCreatedOnField().setText("");
        employeeView.getLastModifiedField().setText("");
    }

    private void cleanAfterExecute() {
        if (employeeView.getCleanAfterCheckBox().isSelected()) {
            cleanForm();
        }
    }

    private void renderedLogs() {
        LogView logView = new LogView();
        LogController logController = new LogController(logView);
        logView.addController(logController);
        logView.setVisible(true);
        logView.setLocationRelativeTo(null);
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
        if (e.getSource().equals(employeeView.getExitButton())) {
            employeeView.dispose();
        }
        if (e.getSource().equals(employeeView.getErrorsLogs())) {
            renderedLogs();
        }
    }


}
