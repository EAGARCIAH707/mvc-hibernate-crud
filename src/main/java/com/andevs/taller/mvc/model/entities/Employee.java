package com.andevs.taller.mvc.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "employee_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Basic
    @Column(name = "document_number")
    private Long documentNumber;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "surname")
    private String surname;

    @Basic
    @Column(name = "second_surname")
    private String secondSurname;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "created_on")
    private Date cretaedOn;

    @Basic
    @Column(name = "last_modified")
    private Date lastModified;
}
