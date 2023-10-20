# jpa-hibernate - Setup Instructions

This guide will help you set up the project by downloading the H2 Database and launching the H2 DB server.

## Prerequisites

- Git installed on your system
- Java Development Kit (JDK) installed
- Docker (optional)

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

## Source
Repository https://github.com/koushikkothagal

next: 39
