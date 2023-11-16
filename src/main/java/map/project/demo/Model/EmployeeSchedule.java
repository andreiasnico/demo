package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;

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

    @Column(name = "startTime")
    private LocalTime startTime;

    @Column(name = "endTime")
    private LocalTime endTime;
}
