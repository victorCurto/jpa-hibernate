package io.javabrains.springbootjpaexample;


import io.javabrains.springbootjpaexample.models.SimpleEmployee;
import io.javabrains.springbootjpaexample.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/*
* Imp - related to transaction we have to options:
*  - jakarta.transaction.Transactional;
*  - org.springframework.transaction.annotation.Transactional;
 */


@SpringBootApplication
@EnableTransactionManagement
public class SpringbootjpaexampleApplication {


    //First approach - inject the EntityManagerFactory
    @PersistenceUnit // This is kind of autowired but with more context, inject an object EntityManagerFactory that is defined from the persistence Unit
    private EntityManagerFactory emf;

    //Secont approach - inject the EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager1; // I need to manager the transaction



    //Third approach - inject Repository
    @Autowired
    EmployeeRepository employeeRepository;


    public static void main(String[] args) {
        SpringApplication.run(SpringbootjpaexampleApplication.class, args);
    }

    @PostConstruct // ensure that this method runs when the application starts
    @Transactional(readOnly = true)
    public void tart(){
        SimpleEmployee e = new SimpleEmployee();
        e.setAge(20);
        e.setName("Vitor Silva");
        e.setSsn("12345");
        e.setDob(new Date());

/*
        //First approach with EntityManagerFactory
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(e);
        transaction.commit();
        entityManager.close(); // We close because I create this object, I don't close the EntityManagerFactory because is spring that handle the lifecycle of this object
*/

/*
        //Second Approach with EntityManager  - Injecting and using shared Persistence Context
        SimpleEmployee employee = entityManager.find(SimpleEmployee.class, 1);
        System.out.println(employee);
*/

        Optional<SimpleEmployee> employeebyId = employeeRepository.findById(1);
        System.out.println(employeebyId.orElse(new SimpleEmployee()));

        if(employeebyId.isPresent())
            updateEmployee(employeebyId.get()); //example of how to manage a transaction in spring boot

    }

    @Transactional
    private void updateEmployeeAndAccessCard(){
        //employeeRepository.save(e);
        updateEmployee(e); // transaction propagation - by default the @Transaction will leverage an existing transaction (if already exists one transaction don't create a new one)
        accessCardRepository.save(a);
    }

    //@Transactional(rollbackOn = SQLException.class) //(declarative approach) the rollback specify it will only rollback if SQLException.class
    //@Transactional(value = TxType.REQUIRED) // Default
    //@Transactional(value = TxType.REQUIRES_NEW) // Don't care if exists an existing one, I will create a new one transaction for this method
    //@Transactional(value = TxType.NOT_SUPPORTED) // Specify that this method should not be a part of a transaction - if this method is called when a transaction is happening is WRONG, it will throw an error
    @Transactional(value = TxType.MANDATORY) //Specify that this method needs a transaction but it will not crate a new one (the caller must provide the transaction)
    private void updateEmployee(SimpleEmployee simpleEmployee){
        // Get Transaction (imperative approach)
        // Start Transaction (imperative approach)
        simpleEmployee.setName("Vitor Gil");
        employeeRepository.save(simpleEmployee);
        // End Transaction (imperative approach)
        // Handle rollback (imperative approach)
    }

}
