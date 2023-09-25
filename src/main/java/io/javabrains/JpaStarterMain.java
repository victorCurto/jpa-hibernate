package io.javabrains;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterMain {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("Foo1 Bar");
        employee.setSsn("123");
        employee.setDob(new Date());
        employee.setAge(20);
        employee.setType(EmployeeType.CONTRACTOR);

        Employee employee1 = new Employee();
        employee1.setName("Foo2 Bar");
        employee1.setSsn("1234");
        employee1.setDob(new Date());
        employee1.setAge(30);
        employee1.setType(EmployeeType.FULL_TIME);


        // 3 get an entity manager from a factory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(employee);
        entityManager.persist(employee1);
        transaction.commit();

        entityManager.close();
        factory.close();
    }
}