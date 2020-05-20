package com.andevs.taller.mvc.model.dao.employee;

import com.andevs.taller.mvc.model.entities.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeDAO {

    Boolean create(Map<String, String> properties);

    Boolean update(Map<String, String> properties);

    Boolean delete(Integer id);

    Employee findByDocNumber(Long docNumber);

    List<Employee> findAll();
}
