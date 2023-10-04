package io.javabrains;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "ACCESS_CARD")
@Data
public class AccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date issuedDate;
    private boolean isActive;
    private String firmwareVersion;

    /*
    (mappedBy = "card")
    * This indicates that in the Employee the "card" is mapped to this AccessCard (I don't need to join one more time)
    * Don't create a new column with the foreing key of an Employee owner -  is considered the non-owning side of the relationship
    * */
/*    @OneToOne ( mappedBy = "card" )
    private Employee owner;*/

}
