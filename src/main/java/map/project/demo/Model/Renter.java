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
    @GeneratedValue
    private Long renterId;

    @Column(name = "firmName")
    private String firmName;

    @Column(name = "email")
    private String email;

    @Column(name = "IBAN")
    private String IBAN;

    @OneToMany(mappedBy = "renter" , cascade = CascadeType.ALL)
    List<RenterEmployee> renterEmployees;

    @OneToMany(mappedBy = "renter" , cascade = CascadeType.ALL)
    List<Unit> units;
}
