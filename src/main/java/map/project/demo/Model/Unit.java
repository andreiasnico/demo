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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "unit" , cascade = CascadeType.ALL)
    List<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "renterId")
    private Renter renter;

    @OneToMany(mappedBy = "unit", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<EmployeeSchedule> employeeSchedules;

    /**
     * god forbid we do the same stuff without proper annotations
     * XD
     */
    @ManyToMany
    private List<Employee> employees;
    @Override
    public String toString() {
        return "Unit{" +
                "unitId=" + unitId +
                ", name='" + name + '\'' +
                ", storyNumber=" + storyNumber +
                ", surface=" + surface +
                '}';
    }
}
