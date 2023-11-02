package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter

public class EmployeeSchedule {

    @EmbeddedId
    private ScheduleKey scheduleKey;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @ManyToOne
    @MapsId("unitId")
    @JoinColumn(name = "unitId")
    private Unit unit;
}
