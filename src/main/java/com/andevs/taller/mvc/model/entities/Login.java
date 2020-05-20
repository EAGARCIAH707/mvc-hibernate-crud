package com.andevs.taller.mvc.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "login")
public class Login implements java.io.Serializable {

    @Id
    @Column(name = "login_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loginId;

    @Basic
    @Column(name = "id_person")
    private Integer person;

    @Basic
    @Column(name = "user")
    private String user;

    @Basic
    @Column(name = "password")
    private String password;
}
