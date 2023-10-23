package io.javabrains.springbootjpaexample.repository;

import io.javabrains.springbootjpaexample.models.SimpleEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<SimpleEmployee, Integer> {

    //fetching an emp
    //persist
    //update(e)
    //find all emps

}
