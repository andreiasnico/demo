package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table(name = "Renter")
public class Renter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long renterId;

    @Column(name = "firmName")
    private String firmName;

    @Column(name = "email")
    private String email;

    @Column(name = "IBAN")
    private String IBAN;

    @OneToMany(mappedBy = "renter" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    List<RenterEmployee> renterEmployees;

    @OneToMany(mappedBy = "renter" , cascade = CascadeType.ALL)
    List<Unit> units;

    @Override
    public String toString() {
        return "Renter{" +
                "renterId=" + renterId +
                ", firmName='" + firmName + '\'' +
                ", email='" + email + '\'' +
                ", IBAN='" + IBAN + '\'' +
                '}';
    }
}
