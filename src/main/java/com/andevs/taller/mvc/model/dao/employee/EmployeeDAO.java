package com.andevs.taller.mvc.model.dao.employee;

import com.andevs.taller.mvc.model.entities.Employee;
import com.andevs.taller.mvc.model.repository.employee.EmployeeRepository;
import com.andevs.taller.mvc.model.repository.employee.IEmployeeRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class EmployeeDAO implements IEmployeeDAO {
    private final IEmployeeRepository employeeRepository;

    public EmployeeDAO() {
        employeeRepository = new EmployeeRepository();
    }


    public Boolean create(Map<String, String> params) {
        Employee employee = getAttributes(params);
        employee.setCretaedOn(new Timestamp(new Date().getTime()));
        employee.setLastModified(new Timestamp(new Date().getTime()));
        return employeeRepository.save(employee);
    }

    public Boolean update(Map<String, String> params) {
        Employee employee = getAttributes(params);
        return employeeRepository.update(employee);
    }

    public void delete(Integer id) {
        employeeRepository.delete(id);
    }

    public Employee findByDocNumber(Long docNumber) {
        return employeeRepository.findByDocNumber(docNumber);
    }

    private Employee getAttributes(Map<String, String> params) {
        Integer employeeId = null;
        if (params.get("employeeId") != null && !params.get("employeeId").equals("")) {
            employeeId = Integer.parseInt(params.get("employeeId"));
        }
        Long documentNumber = Long.parseLong(params.get("documentNumber"));
        String name = params.get("name");
        String surname = params.get("surname");
        String secondSurname = params.get("secondSurname");
        String phone = params.get("phone");
        return Employee.builder()
                .employeeId(employeeId)
                .documentNumber(documentNumber)
                .name(name)
                .surname(surname)
                .secondSurname(secondSurname)
                .phone(phone)
                .build();
    }
}
