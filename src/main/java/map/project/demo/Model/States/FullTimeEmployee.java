package map.project.demo.Model.States;

import map.project.demo.Model.Employee;
import map.project.demo.Model.EmployeeSchedule;

import java.time.Duration;

public class FullTimeEmployee implements StateInterface {

    /**
     * handle for state design pattern
     * @return true or false on the question : does the employee have enough or more hours to work
     */
    @Override
    public boolean handle(Employee employee){
        Long totalTime = 0L;
        for (EmployeeSchedule elem : employee.getEmployeeSchedules()
             ) {
            totalTime += Duration.between(elem.getEndTime() , elem.getStartTime()).toHours();
        }
        if(totalTime >= 40){
            return true;
        }
        return false;
    }
}
