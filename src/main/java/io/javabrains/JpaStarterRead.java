package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaStarterRead {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();



/*
        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println("********************* Fetched Employee");
        System.out.println(employee);
        System.out.println("********************* Fetched card");
        System.out.println("Age: "+ employee.getCard());*/

        AccessCard card = entityManager.find(AccessCard.class, 2);
        System.out.println("Card: "+card);

        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println("Employee: "+ employee);
        System.out.println("Employee.card: "+ employee.getCard());
        //System.out.println("Employee.payStubs: "+ employee.getPayStub());

        PayStub payStub = entityManager.find(PayStub.class, 5);
        System.out.println("PayStub: "+payStub);
        System.out.println("PayStub.employee: "+payStub.getEmployee());
    }
}
