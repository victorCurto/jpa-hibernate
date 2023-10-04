package io.javabrains;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "EMPLOYEE_DATA")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "age")
    private int age;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @Transient
    private String debugString;

    //private int accessCardId;

    @OneToOne(cascade = CascadeType.ALL) //Owns the relationship - create a card_id with the foreign Key to card
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private AccessCard card;

    @OneToMany(mappedBy = "employee") //Employee -> OneToMany -> List<PayStub>
    private List<PayStub> payStub;

    private transient String debugString2;

    @Column(name = "employee_ssn", unique = true, length = 10, nullable = false, updatable = false)
    private String ssn;

}
