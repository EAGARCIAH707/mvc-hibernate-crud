package com.andevs.taller.mvc.controller;

import com.andevs.taller.mvc.model.dao.employee.EmployeeDAO;
import com.andevs.taller.mvc.model.dao.employee.IEmployeeDAO;
import com.andevs.taller.mvc.model.entities.Employee;
import com.andevs.taller.mvc.model.util.log.LogUtil;
import com.andevs.taller.mvc.view.table.EmployeeTableView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TableEmployeeController implements ActionListener {
    private final EmployeeTableView employeeTableView;
    private IEmployeeDAO employeeDAO;

    public TableEmployeeController(EmployeeTableView employeeTableView) {
        this.employeeTableView = employeeTableView;
        employeeDAO = new EmployeeDAO();
        tableWrite();
    }

    private void tableWrite() {
        List<Employee> employeeList = employeeDAO.findAll();
        String[][] data = new String[employeeList.size()][8];
        String[] header = {"Id", "Documento", "Nombre", "Apellido1", "Apellido2", "Telefono", "Creado", "Actualizado"};
        int i = 0;
        for (Employee employee : employeeList) {
            try {
                data[i][0] = employee.getEmployeeId().toString();
                data[i][1] = employee.getDocumentNumber().toString();
                data[i][2] = employee.getName();
                data[i][3] = employee.getSurname();
                data[i][4] = employee.getSecondSurname();
                data[i][5] = employee.getPhone();
                data[i][6] = employee.getCretaedOn().toString();
                data[i][7] = employee.getLastModified().toString();
                i++;
            } catch (Exception e) {
                LogUtil.writeInLog("Error in record table");
            }
            employeeTableView.getEmployeeTable().setModel(new DefaultTableModel(data, header));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(employeeTableView.getExitButton())) {
            employeeTableView.dispose();
        }
    }
}
