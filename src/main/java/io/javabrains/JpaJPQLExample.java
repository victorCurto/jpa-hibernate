package io.javabrains;

import javax.persistence.*;
import java.util.List;

public class JpaJPQLExample {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();

/*
        // SQL = select * from employee_data where age > 25;
        TypedQuery<Employee> query1 = entityManager.createQuery("select e from Employee e where e.age > 25", Employee.class);
        List<Employee> resultList1 = query1.getResultList();
        resultList1.forEach(System.out::println);
*/

/*
        // order by
        TypedQuery<Employee> query2 = entityManager.createQuery("select e from Employee e order by e.age desc", Employee.class);
        List<Employee> resultList2 = query2.getResultList();
        resultList2.forEach(System.out::println);
*/

/*
        // like
        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.name like '%Bar'", Employee.class);
        List<Employee> resultList = query.getResultList();
        resultList.forEach(System.out::println);
*/

/*
        // between
        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.age between 19 and 29", Employee.class);
        List<Employee> resultList = query.getResultList();
        resultList.forEach(System.out::println);
*/

/*
        // In JPQL the joins are already made
        // SQL select e.* from employee_data e join access_card ac on e.card_id = ac.id where ac.isactive = true;
        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.card.isActive = true", Employee.class);
        List<Employee> resultList = query.getResultList();
        resultList.forEach(System.out::println);
*/

/*
        // Getting just a simple value
        //SQL select name from employee_data;
        TypedQuery<String> query = entityManager.createQuery("select e.name from Employee e", String.class);
        List<String> resultList = query.getResultList();
        resultList.forEach(System.out::println);

        TypedQuery<Object[]> query1 = entityManager.createQuery("select e.name, e.age, e.dob from Employee e", Object[].class);
        List<Object[]> resultList1 = query1.getResultList();
        resultList1.forEach(e -> System.out.println(e[0]+" - "+e[1]+" - "+e[2]));
*/

/*
        //Avoiding SQL injection with JPQL parameters
        int minAge = 25;
        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.age > :minAge", Employee.class);
        query.setParameter("minAge", minAge);

        List<Employee> resultList = query.getResultList();
        resultList.forEach(System.out::println);
*/

        //Named queries
        TypedQuery<Employee> query = entityManager.createNamedQuery("emp name acs", Employee.class);
        List<Employee> resultList = query.getResultList();
        resultList.forEach(System.out::println);

        System.out.println("#########################################");
//        TypedQuery<Employee> query2 = entityManager.createNamedQuery("emp name and age acs", Employee.class);
//        query2.setParameter("age", 25);
//        List<Employee> resultList2 = query2.getResultList();
//        resultList.forEach(System.out::println);

        entityManager.close();
        factory.close();
    }
}
