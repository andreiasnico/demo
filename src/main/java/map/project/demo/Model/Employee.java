package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import map.project.demo.Model.States.State;

import java.util.List;

@Data
@Entity
@Setter
@Getter
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private Titles title;

    @Column(name = "salary")
    private Long salary;

    @OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<EmployeeSchedule> employeeSchedules;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId" , referencedColumnName = "employeeId")
    private EntryCard card;
    /**
     * attribute for the state pattern
     */
    @Column(name = "state")
    private State state;

    @ManyToMany
    private List<Unit> units;


    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", title=" + title +
                ", salary=" + salary +
                ", card=" + card +
                ", state=" + state +
                '}';
    }
}
