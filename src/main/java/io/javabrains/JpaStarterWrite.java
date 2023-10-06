package io.javabrains;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterWrite {

    public static void main(String[] args) {
        /*
        Employee employee = new Employee();
        employee.setName("Foo1 Bar");
        employee.setSsn("123");
        employee.setDob(new Date());
        employee.setAge(20);
        employee.setType(EmployeeType.CONTRACTOR);


        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // Update
        entityManager.persist(employee1);


        // Remove an entity
        entityManager.remove(employee1);
        transaction.commit();
        entityManager.close();
        factory.close();

        */

        // Save a new Entity
        Employee employee1 = new Employee();
        employee1.setName("Foo2 Bar");
        employee1.setSsn("1234");
        employee1.setDob(new Date());
        employee1.setAge(30);
        employee1.setType(EmployeeType.FULL_TIME);


        Employee employee2 = new Employee();
        employee2.setName("Foo1 Bar");
        employee2.setSsn("123");
        employee2.setDob(new Date());
        employee2.setAge(20);
        employee2.setType(EmployeeType.CONTRACTOR);


        AccessCard card1 = new AccessCard();
        card1.setIssuedDate(new Date());
        card1.setActive(true);
        card1.setFirmwareVersion("1.0.0");
        card1.setOwner(employee1);
        employee1.setCard(card1);

        AccessCard card2 = new AccessCard();
        card2.setIssuedDate(new Date());
        card2.setActive(false);
        card2.setFirmwareVersion("1.2.0");
        card2.setOwner(employee2);
        employee2.setCard(card2);

        PayStub payStub1 = new PayStub();
        payStub1.setPayPeriodStart(new Date());
        payStub1.setPayPeriodEnd(new Date());
        payStub1.setSalary(1000);
        payStub1.setEmployee(employee1);
        employee1.addPayStub(payStub1);

        PayStub payStub2 = new PayStub();
        payStub2.setPayPeriodStart(new Date());
        payStub2.setPayPeriodEnd(new Date());
        payStub2.setSalary(2000);
        payStub2.setEmployee(employee1);
        employee1.addPayStub(payStub2);

        EmailGroup group1 = new EmailGroup();
        group1.setName("Company Watercooler discussions");
        group1.addMember(employee1);
        group1.addMember(employee2);
        employee1.addEmailSubscription(group1);
        employee2.addEmailSubscription(group1);

        EmailGroup group2 = new EmailGroup();
        group2.setName("Engineering");
        employee1.addEmailSubscription(group2);
        group2.addMember(employee1);



        // 3 get an entity manager from a factory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(employee1);
        entityManager.persist(employee2);

        entityManager.persist(card1);
        entityManager.persist(card2);

        entityManager.persist(payStub1);
        entityManager.persist(payStub2);

        entityManager.persist(group1);
        entityManager.persist(group2);

        transaction.commit();

        entityManager.close();
        factory.close();
    }
}