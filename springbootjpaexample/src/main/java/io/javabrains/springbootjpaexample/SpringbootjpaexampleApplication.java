package io.javabrains.springbootjpaexample;


import io.javabrains.springbootjpaexample.models.SimpleEmployee;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringbootjpaexampleApplication {

    @PersistenceUnit // This is kind of autowired but with more context, inject an object EntityManagerFactory that is defined from the persistence Unit
    private EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootjpaexampleApplication.class, args);
    }

    @PostConstruct // ensure that this method runs when the application starts
    public void tart(){
        SimpleEmployee e = new SimpleEmployee();
        e.setAge(20);
        e.setName("Vitor Silva");
        e.setSsn("12345");
        e.setDob(new Date());

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(e);
        transaction.commit();
        entityManager.close();

    }

}
