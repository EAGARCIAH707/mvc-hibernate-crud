package com.andevs.taller.mvc.model.repository.employee;

import com.andevs.taller.mvc.model.entities.Employee;

import java.util.List;

public interface IEmployeeRepository {
    Boolean save(Employee employee);

    Boolean update(Employee employee);

    Boolean delete(Integer id);

    Employee findByDocNumber(Long docNumber);

    Employee findById(Integer id);

    List<Employee> findAll();
}
