package io.javabrains;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class PayStub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "start")
    private Date payPeriodStart;

    @Column(name = "ended")
    private Date payPeriodEnd;

    private float salary;

    /*
    * The PayStub owns the relationship (it will be added a new column with the foreign key) so:
    *  Many PayStubs belongs to One employee: ( PayStub -> @ManyToOne -> Employee )
    * */
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Override
    public String toString() {
        return "PayStub{" +
            "id=" + id +
            ", payPeriodStart=" + payPeriodStart +
            ", payPeriodEnd=" + payPeriodEnd +
            ", salary=" + salary +
            ", employee=" + employee.getName() +    // Dangerous, be carefully with  "recursive" toString()
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
        PayStub payStub = (PayStub) o;
        return id == payStub.id && Float.compare(payStub.salary, salary) == 0 && Objects.equals(payPeriodStart, payStub.payPeriodStart)
            && Objects.equals(payPeriodEnd, payStub.payPeriodEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payPeriodStart, payPeriodEnd, salary);
    }
}
