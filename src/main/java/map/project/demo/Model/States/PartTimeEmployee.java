package map.project.demo.Model.States;

import map.project.demo.Model.Employee;
import map.project.demo.Model.EmployeeSchedule;

import java.time.Duration;

public class PartTimeEmployee  implements StateInterface {

    @Override
    public   boolean handle(Employee employee){
        Long totalTime = 0L;
        for (EmployeeSchedule elem : employee.getEmployeeSchedules()
        ) {
            totalTime += Duration.between(elem.getEndTime() , elem.getStartTime()).toHours();
        }
        if(totalTime >= 20){
            return true;
        }
        return false;
    }
}
