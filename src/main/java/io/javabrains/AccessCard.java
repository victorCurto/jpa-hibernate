package io.javabrains;

import java.util.Date;
import java.util.Objects;
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
    @OneToOne( mappedBy = "card" )
    private Employee owner;

    @Override
    public String toString() {
        return "AccessCard{" +
            "id=" + id +
            ", issuedDate=" + issuedDate +
            ", isActive=" + isActive +
            ", firmwareVersion='" + firmwareVersion + '\'' +
            ", owner=" + owner.hashCode() +     // Dangerous, be carefully with  "recursive" toString()
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccessCard that = (AccessCard) o;
        return id == that.id && isActive == that.isActive && Objects.equals(issuedDate, that.issuedDate) && Objects.equals(firmwareVersion,
            that.firmwareVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issuedDate, isActive, firmwareVersion);
    }
}
