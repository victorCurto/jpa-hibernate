package io.javabrains.springbootjpaexample.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EMPLOYEE_DATA")
@Data
@NamedQuery(query="select e from Employee e order by e.name", name="emp name acs")
@NamedQuery(query="select e from Employee e where e.age > :age order by e.name", name="emp name and age acs")
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


    @OneToOne(cascade = CascadeType.ALL) //Owns the relationship - create a card_id with the foreign Key to card
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private AccessCard card;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE) //Employee -> OneToMany -> List<PayStub>
    private List<PayStub> payStub = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMAIL_GROUP_SUBSCRIPTIONS",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "subscription_email_id")
    )
    private List<EmailGroup> emailGroups = new ArrayList<>();

    private transient String debugString2;

    @Column(name = "employee_ssn", unique = true, length = 10, nullable = false, updatable = false)
    private String ssn;


    public void addPayStub(PayStub payStub){
        this.payStub.add(payStub);
    }

    public void addEmailSubscription(EmailGroup group){
        this.emailGroups.add(group);
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + id +
            ", age=" + age +
            ", name='" + name + '\'' +
            ", dob=" + dob +
            ", type=" + type +
            ", debugString='" + debugString + '\'' +
            ", card_id=" + card.getId() +  // Dangerous, be carefully with  "recursive" toString()
            //", payStub size=" + payStub.size() + // Dangerous, be carefully with  "recursive" toString()
            ", debugString2='" + debugString2 + '\'' +
            ", ssn='" + ssn + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && Objects.equals(name, employee.name) && Objects.equals(dob, employee.dob)
            && type == employee.type && Objects.equals(debugString, employee.debugString) && Objects.equals(debugString2, employee.debugString2)
            && Objects.equals(ssn, employee.ssn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name, dob, type, debugString, debugString2, ssn);
    }
}
