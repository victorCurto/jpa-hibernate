package io.javabrains;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PayStub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date payPeriodStart;
    private Date payPeriodEnd;
    private float salary;

    /*
    * The PayStub owns the relationship (it will be added a new column with the foreign key) so:
    *  Many PayStubs belongs to One employee: ( PayStub -> @ManyToOne -> Employee )
    * */
    @ManyToOne
    private Employee employee;

}
