# JPA

### Annotations:
=> class:<br>
@Entity -> specifies an class that is an entity
@Table(name = "TABLE_NAME") -> name the table in the DB

=> members:<br>
@Column(name = "employee_ssn", unique = true, length = 10, nullable = false, updatable = false) -> configure the column
@Temporal(TemporalType.DATE) -> applied to temporal variables
@Transient -> applied to mark as transient, don't save in the DB

@Id -> specifies a primary key 
@GeneratedValue(strategy = GenerationType.AUTO) -> specify how the DB will generate the pk

----

### Modeling a One to One relationship

=> SQL side of things
The relationship is made with keys (primary key and forenge key)


=> Java side of things
The relationship is made with Objects/instances (and apply the annotations to tell the relationship to JPA )
ex.
```
Object1.setObject2(object2);

public class Object1{
	.......
	@OneToOne()  //tell the JPA to mappe the key
	private Object2 object2;
	.......
}
```
######################	The owner of the relationship	######################

=> Fetching and Fetch Types:

@OneToOne(fetch = FetchType.EAGER) -> default in OneToOne, performs the join table upfront

@OneToOne(fetch = FetchType.LAZY) -> We only make the query after we "call the object in the java code"


Source: TODO
https://www.baeldung.com/jpa-one-to-one

1) Using a Foreign Key - Modeling With a Foreign Key

2) Using a Shared Primary Key - Modeling With a Shared Primary Key

3) Using a Join Table - Modeling With a Join Table



###  Modeling a One to Many relationship

### Modeling Many to Many relationship

----
### Cache

=> Persistence Context as a first level cache
Before the "transaction.commit();" all the changes are 'cached' by the JPA provider (hibernate) and will be readed from this cache

	 ____(hibernate)____      _____
	|Persistence context| <-> | DB |
	 -------------------      -----

Note: the JPA provider it will try to avoid the maximum of calls to the DB


### Entity Life cycle
Possible States of entities:

	1) Transient: when we create the object instance of entity (nothing related to the DB)
	
	2) Managed: JPA will manage the entity, when:
		- we create the instance and we call "persist(e)"
		- we call "find()" and JPA create the instance

	
	3) Removed: an entity goes to this state when we call "remove()"
		- if we call "persist(e)" after the "remove()" it will be back to the "Managed" state
	
	
	4) Detached: remove an entity from the "Managed" state but not from DB, 
		- we call "detach(e)" and then any change in this instance it will not be managed by the JPA
		- We call "merge(e)" and then the entity will be managed/tracked again by the JPA
		- We call "clear()" and apply to all the entities that are in the persistence context at this point
		- We call "refresh(e)" and it will set the original state (JPA will query the DB and update with the DB values)
Note:
=> We have the "flush()" method that forces all the state (insert/update/delete) that is in the "persistence context" to the writen to the DB !!!!



### JPQL - Java Persistence Query Language