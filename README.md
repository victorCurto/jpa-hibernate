# jpa-hibernate - Setup Instructions

This guide will help you set up the project by downloading the H2 Database and launching the H2 DB server.

## Prerequisites

- git version 2.25.1
```
 $ git version
```
- Java 17 Development Kit (JDK)
```
 $ java -version
```
- Apache Maven 3.9.1
```
$ mvn -version
```
- Docker (optional)
```
$ docker -v
``` 

## Setup

### Dependencies (external Libraries)
- hibernate-core-5.5.6.Final.jar (Hibernate)
- javax.persistence-api-2.2.jar (JPA)
- lombok-1.18.30.jar (project Lombok)
- postgresql-42.6.0.jar or h2-2.2.224.jar (DB driver)

### With docker (postgres Database):
```bash
//In case of Docker daemon is down
$ service docker start

// launch the db
$ docker run -p 5432:5432 -e POSTGRES_PASSWORD=postgres --name pg1 postgres

// re-start the container 
$ docker start pg1
```
Default credentials:
- user: postgres
- password: postgres


### With H2 Database:

#### 1. Download the H2 Database

You can download the H2 Database from [https://www.h2database.com/html/download.html](https://www.h2database.com/html/download.html).

#### 2. Launch the H2 DB server

##### Windows

```bash
$ cd h2-2023-09-17\h2\bin
$ .\h2.bat

```

##### Linux/Mac
```bash
$ cd h2-2023-09-17/h2/bin
$ chmod +x h2.sh
$ ./h2.sh
```

## This project has 2 parts
### 1) jpa-hibernate
This is a maven project with just JPA and Hibernate dependencies to explore all the JPA features
####Structure

> persistence.xml (src/main/resources/META-INF/persistence.xml) <br>

Persistence unit with all the information where the DB is and how to connect to it



### 2) SpringBoot JPA example
This is a module in the project that consist in a SpringBoot project with 2 dependencies:
1. spring-boot-starter-data-jpa    
2. h2 / Postgres

The way to provide data for the persistence context is by using a properties file<br>
__The 'Spring boot JPA API' is a wrapper for the 'JPA API'__


In a SpringBoot JPA project we can have multiple approaches:
1) Inject EntityManagerFactory - used to read and write
2) EntityManager - shared entity manager - used to read (because threads)
3) Spring Repository interface (ex. CrudRepository) -  Spring boot wrapper about JPA
   - declarative transaction manager (the concept of proxy)
     Spring boot allow us to do declarative transactions instead of imperative transactions
```
   @EnableTransactionManagement        
   @Transactional
```

```
   @Repository
   public interface MyRepository extends CrudRepository<MyEntity, PrimaryKeyType> {

```



Related to transactions we have two ways of implement them:
1)  - jakarta.transaction.Transactional;
2)  - org.springframework.transaction.annotation.Transactional;

Options:
```
@Transactional(value = TxType.REQUIRED) // Default
@Transactional(value = TxType.REQUIRES_NEW) // Don't care if exists an existing one, I will create a new one transaction for this method
@Transactional(value = TxType.NOT_SUPPORTED) // Specify that this method should not be a part of a transaction - if this method is called when a transaction is happening is WRONG, it will throw an error
@Transactional(value = TxType.MANDATORY) //Specify that this method needs a transaction but it will not crate a new one (the caller must provide the transaction)
```

In a transaction you can specify the way/when you will do a rollbacK:
```
@Transactional(rollbackOn = SQLException.class) //(declarative approach) the rollback specify it will only rollback if SQLException.class
```



__Read only transactions__:<br>
When you want for example three different entities put them all together in one dataStructure and send to the user
Warning: maybe between the different reads happens a write....
```
@Transactional(readOnly = true) // I specify thant I'm reading and not block any other transaction (improve preformance)
```


## Source
Repository https://github.com/koushikkothagal

next: 40
