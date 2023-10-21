package io.javabrains.springbootjpaexample.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class SimpleEmployee {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int age;


    @Temporal(TemporalType.TIME)
    private Date dob;

    @Column(name = "employee_ssn", unique = true, length = 10, nullable = false, updatable = false)
    private String ssn;

}
