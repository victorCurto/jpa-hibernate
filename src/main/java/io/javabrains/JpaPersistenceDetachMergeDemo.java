package io.javabrains;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaPersistenceDetachMergeDemo {

    public static void main(String[] args) {

        // 1 Create a new Entity
        Employee employee1 = new Employee();
        employee1.setName("New Employee");
        employee1.setSsn("12345");
        employee1.setDob(new Date());
        employee1.setAge(25);
        employee1.setType(EmployeeType.FULL_TIME);

        // 2 get an entity manager from a factory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        employee1.setAge(45);
        entityManager.flush();
        //entityManager.detach(employee1); // app to one particular entity
        entityManager.clear();  // apply to all the entities that are in the persistence context at this point



        Employee managedEmployee = entityManager.find(Employee.class, 1);

        managedEmployee.setAge(23);
        managedEmployee.setDob(null);


        entityManager.detach(managedEmployee);//if I don't want to persist
        //OR (if I don't want to detach)
        entityManager.refresh(managedEmployee); // it will set the original state (JPA will query the DB and update with the DB values)

//        fixEmployee(employee1);
//        entityManager.merge(employee1);

        transaction.commit();
        entityManager.close();
        factory.close();
    }

    private static void fixEmployee(Employee employee1){
        employee1.setAge(50);
    }

    private static void debugEmployee(Employee employee1){
        employee1.setAge(100);
        employee1.setSsn("9999");
    }
}