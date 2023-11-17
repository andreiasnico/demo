package map.project.demo.UI;

import map.project.demo.Model.EmployeeSchedule;
import map.project.demo.Service.EmployeeScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class EmployeeScheduleCliCommnads {
    @Autowired
    private EmployeeScheduleService employeeScheduleService;

    @ShellMethod(key = "EmployeeSchedule", value = "show all employee schedules")
    public String allEmployeeSchedules() {
        return employeeScheduleService.findAll().toString();
    }
}
