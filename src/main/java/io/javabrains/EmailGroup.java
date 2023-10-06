package io.javabrains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class EmailGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "emailGroups", fetch = FetchType.LAZY)
    private List<Employee> members = new ArrayList<>();
    /*
     * To create a many to many relationship I need and extra "join table"
     *   EmployID | EmailGroupID
     *       1    |      7
     *       1    |      8
     *       2    |      7
     *
     */

    public void addMember(Employee employee) {
        this.members.add(employee);
    }

}
