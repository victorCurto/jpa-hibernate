package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterUpdate {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();

        //Get the entities/objects
        Employee employee = entityManager.find(Employee.class, 3);
        EmailGroup emailGroup = entityManager.find(EmailGroup.class, 8);
        //Update
        employee.addEmailSubscription(emailGroup);
        emailGroup.addMember(employee);


        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //Persist the updated values
        entityManager.persist(emailGroup);
        entityManager.persist(employee);

        transaction.commit();
        entityManager.close();
        factory.close();
    }
}
