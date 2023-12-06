package map.project.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ScheduleKey implements Serializable {

    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "unitId")
    private Long unitId;

}
