package com.andevs.taller.mvc.model.dao.employee;

import com.andevs.taller.mvc.model.entities.Employee;
import com.andevs.taller.mvc.model.repository.employee.EmployeeRepository;
import com.andevs.taller.mvc.model.repository.employee.IEmployeeRepository;
import com.andevs.taller.mvc.model.util.log.LogUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
        employee.setLastModified(new Timestamp(new Date().getTime()));
        return employeeRepository.update(employee);
    }

    public Boolean delete(Integer id) {
        if (id != null && id > 0) {
            return employeeRepository.delete(id);
        }
        return Boolean.FALSE;
    }

    public Employee findByDocNumber(Long docNumber) {
        return employeeRepository.findByDocNumber(docNumber);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    private Employee getAttributes(Map<String, String> params) {
        try {
            Integer employeeId = null;
            if (params.get("employeeId") != null && !params.get("employeeId").equals("")) {
                employeeId = Integer.parseInt(params.get("employeeId"));
            }
            Long documentNumber = Long.parseLong(params.get("documentNumber"));
            String name = params.get("name");
            String surname = params.get("surname");
            String secondSurname = params.get("secondSurname");
            String phone = params.get("phone");
            Timestamp createdOn = null;
            String date = params.get("cretaedOn");
            if (date != null && !date.equals("")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(date);
                createdOn = new Timestamp(parsedDate.getTime());
            }
            return Employee.builder()
                    .employeeId(employeeId)
                    .documentNumber(documentNumber)
                    .name(name)
                    .surname(surname)
                    .secondSurname(secondSurname)
                    .phone(phone)
                    .cretaedOn(createdOn)
                    .build();
        } catch (Exception e) {
            LogUtil.writeInLog("Error in getAttributes ".concat(e.getMessage()));
        }
        return Employee.builder().build();
    }
}
