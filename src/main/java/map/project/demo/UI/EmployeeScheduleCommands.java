package map.project.demo.UI;

import map.project.demo.Model.Employee;
import map.project.demo.Model.EmployeeSchedule;
import map.project.demo.Model.ScheduleKey;
import map.project.demo.Model.Unit;
import map.project.demo.Service.EmployeeScheduleService;
import map.project.demo.Service.EmployeeService;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalTime;

@ShellComponent
public class EmployeeScheduleCommands {
    @Autowired
    private EmployeeScheduleService employeeScheduleService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UnitService unitService;
    @ShellMethod(key = "see all schedules" , value = "find all schedules")
    public String findAll(){
        return this.employeeScheduleService.findAllSchedules().toString();
    }

    /**
     * method that adds a schedule to our database
     * @param employeeId employee id
     * @param unitId unit id
     * @param startTime start time
     * @param endTime end time
     * @return String of the added Schedule
     */
    @ShellMethod(key = "add schedule" , value = "add a schedule")
    public String addSchedule(@ShellOption(value = {"employeeId"} , help = "employee id") Long employeeId,
                              @ShellOption(value = {"unitId"} , help = "unit id") Long unitId ,
                              @ShellOption(value = {"startTime"} , help = "start time") String startTime,
                              @ShellOption(value = {"endTime"} , help="end time")String endTime){
        EmployeeSchedule employeeSchedule = new EmployeeSchedule();
        employeeSchedule.setEmployee(this.employeeService.findEmployeeById(employeeId));
        employeeSchedule.setUnit(this.unitService.findByUnitId(unitId).get()); // todo change this to make it nice
        employeeSchedule.setStartTime(LocalTime.parse(startTime));
        employeeSchedule.setEndTime(LocalTime.parse(endTime));
        return this.employeeScheduleService.save(employeeSchedule).toString();
    }

    @ShellMethod(key = "delete schedule" , value = "delete a schedule")
    public String deleteSchedule(@ShellOption(value = {"employeeId"} , help = "employee id") Long employeeId,
                                 @ShellOption(value = {"unitId"} , help = "unit id") Long unitId){
        Unit unit = this.unitService.findByUnitId(unitId).get();
        Employee employee = this.employeeService.findEmployeeById(employeeId);

        this.employeeScheduleService.deleteSchedule(employee , unit);
        return "Schedule has been removed";
    }

    @ShellMethod(key = "update schedule" , value = "update a scehdule plan")
    public String updateSchedule(@ShellOption(value = {"employeeId"} , help = "employee id") Long employeeId,
                                 @ShellOption(value = {"unitId"} , help = "unit id") Long unitId,
                                 @ShellOption(value = {"startTime"} , help = "start time of schedule") String startTime,
                                 @ShellOption(value = {"endTime"} , help ="end time of schedule") String endTime){
        EmployeeSchedule employeeSchedule = this.employeeScheduleService.findSchedule(this.employeeService.findEmployeeById(employeeId),
                this.unitService.findByUnitId(unitId).get());
        employeeSchedule.setStartTime(LocalTime.parse(startTime));
        employeeSchedule.setEndTime(LocalTime.parse(endTime));

        return this.employeeScheduleService.save(employeeSchedule).toString();
    }
}
