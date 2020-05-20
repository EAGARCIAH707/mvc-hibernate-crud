package com.andevs.taller.mvc.model.repository.employee;

import com.andevs.taller.mvc.model.entities.Employee;

import java.util.Optional;

public interface IEmployeeRepository {
    Boolean save(Employee employee);

    Boolean update(Employee employee);

    void delete(Integer id);

    void findByDocNumber(Long docNumber);

    Employee findById(Integer id);
}
