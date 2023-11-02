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
@Table(name = "Unit")
public class Unit {

    @Id
    @GeneratedValue
    private Long unitId;

    @Column(name = "name")
    private String name;

    @Column(name = "storyNumber")
    private int storyNumber;

    @Column(name = "surface")
    private Long surface;


    @ManyToOne
    @JoinColumn(name = "buildingId")
    private Building building;

    @OneToMany(mappedBy = "unit")
    List<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "renterId")
    private Renter renter;

    @OneToMany(mappedBy = "unit")
    private List<EmployeeSchedule> employeeSchedules;


}
