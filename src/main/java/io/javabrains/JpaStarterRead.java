package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaStarterRead {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();

        Employee employee = entityManager.find(Employee.class, 1);

        System.out.println("********************* Fetched Employee");
        System.out.println(employee);q
        System.out.println("********************* Fetched card");
        System.out.println("Age: "+ employee.getCard());
    }
}
