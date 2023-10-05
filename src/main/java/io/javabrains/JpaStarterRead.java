package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaStarterRead {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();

/*
        // @OneToOne relationship
        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println("********************* Fetched Employee");
        System.out.println(employee);
        System.out.println("********************* Fetched card");
        System.out.println("Card: "+ employee.getCard());


        AccessCard card = entityManager.find(AccessCard.class, 2);
        System.out.println("********************* Fetched card");
        System.out.println("Card: "+card);
        System.out.println("********************* Fetched Employee from card");
        System.out.println("Employee from card: "+card.getOwner());
*/

        // @ManyToOne and @OneToMany relationship
        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println("********************* Fetched Employee");
        System.out.println("Employee: " + employee);
//        System.out.println("********************* Fetched card from employee");
//        System.out.println("Employee.card: " + employee.getCard());
        System.out.println("********************* Fetched payStubs from employee");
        System.out.println("Employee.payStubs: " + employee.getPayStub());

        // Fetching payStubs
//        System.out.println("********************* Fetched PayStubs");
//        PayStub payStub = entityManager.find(PayStub.class, 5);
//        System.out.println("PayStub: " + payStub);
//        System.out.println("PayStub.employee: " + payStub.getEmployee());

    }
}
