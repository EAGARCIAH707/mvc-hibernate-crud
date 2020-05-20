package com.andevs.taller.mvc.main;

import com.andevs.taller.mvc.model.repository.employee.EmployeeRepository;
import com.andevs.taller.mvc.model.repository.login.LoginRepository;

import java.sql.SQLException;

public class MainApplication {
    public static void main(String[] args) throws SQLException {
        LoginRepository loginRepository = new LoginRepository();
        loginRepository.login("admin", "admin");
        EmployeeRepository employeeRepository = new EmployeeRepository();
/*        Boolean r = employeeRepository.save(Employee.builder()
                .documentNumber((long) 1110597671)
                .name("Edisson")
                .surname("garcia")
                .secondSurname("herrera")
                .phone("3058148988")
                .cretaedOn(new Timestamp(new Date().getTime()))
                .lastModified(new Timestamp(new Date().getTime()))
                .build());
        System.out.println(r);*/
        System.out.println("hola mundo");
    }
}
