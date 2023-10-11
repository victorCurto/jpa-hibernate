package io.javabrains;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaPersistenceContextDemo {

    public static void main(String[] args) {

        // 1 Create a new Entity
        Employee employee1 = new Employee();
        employee1.setName("New Employee");
        employee1.setSsn("12345");
        employee1.setDob(new Date());
        employee1.setAge(35);
        employee1.setType(EmployeeType.FULL_TIME);
        employee1.setCard(new AccessCard());
        System.out.println("*****************************************   Created Employee Instance");

        // 2 get an entity manager from a factory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        System.out.println("*****************************************   Starting the transaction");

        entityManager.persist(employee1);
        System.out.println("*****************************************   After Persist method call");

        Employee employeeFound = entityManager.find(Employee.class, 1);
        System.out.println("Original Employee : "+ employee1);
        System.out.println("Employee found : "+employeeFound);

        transaction.commit();
        System.out.println("*****************************************   After transaction closed");
        entityManager.close();
        factory.close();
    }
}