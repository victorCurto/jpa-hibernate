package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterDelete {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();

        //Get the entities/objects
        Employee employee = entityManager.find(Employee.class, 1);


        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //Remove the entity
        entityManager.remove(employee);

        transaction.commit();
        entityManager.close();
        factory.close();
    }
}
