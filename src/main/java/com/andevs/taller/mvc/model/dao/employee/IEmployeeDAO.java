package com.andevs.taller.mvc.model.dao.employee;

import com.andevs.taller.mvc.model.entities.Employee;

import java.util.Map;

public interface IEmployeeDAO {

    Boolean create(Map<String, String> properties);

    Boolean update(Map<String, String> properties);

    void delete(Integer id);

    void findByDocNumber(Long docNumber);
}
