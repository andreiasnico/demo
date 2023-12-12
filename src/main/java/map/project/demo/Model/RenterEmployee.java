package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Entity
@Getter
@Setter
@Table(name = "RenterEmployee")
public class RenterEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long renterEmployeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "renterId")
    private Renter renter;
}
