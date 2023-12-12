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
import java.util.Optional;

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
        Optional<Employee> employeeOptional = this.employeeService.findEmployeeById(employeeId);
        if(employeeOptional.isEmpty()){
            return "There is no employee with this id";
        }
        Optional<Unit> unit = this.unitService.findByUnitId(unitId);
        if(unit.isEmpty()){
            return "There is no unit with this id";
        }
        EmployeeSchedule employeeSchedule = new EmployeeSchedule();
        ScheduleKey scheduleKey = new ScheduleKey();
        scheduleKey.setEmployeeId(employeeId);
        scheduleKey.setUnitId(unitId);
        employeeSchedule.setScheduleKey(scheduleKey);
        employeeSchedule.setEmployee(employeeOptional.get());
        employeeSchedule.setUnit(unit.get());
        employeeSchedule.setStartTime(LocalTime.parse(startTime));
        employeeSchedule.setEndTime(LocalTime.parse(endTime));
        return this.employeeScheduleService.save(employeeSchedule).toString();
    }

    /**
     * method where we delete a schedule from the database
     * @param employeeId employee id
     * @param unitId unit id
     * @return prompt
     */
    @ShellMethod(key = "delete schedule" , value = "delete a schedule")
    public String deleteSchedule(@ShellOption(value = {"employeeId"} , help = "employee id") Long employeeId,
                                 @ShellOption(value = {"unitId"} , help = "unit id") Long unitId){
        Optional<Unit> unit = this.unitService.findByUnitId(unitId);
        Optional<Employee> employee = this.employeeService.findEmployeeById(employeeId);
        if(unit.isEmpty()){
            return "There is no unit with this id";
        }

        if(employee.isEmpty()){
            return "There is no employee with this id";
        }
        this.employeeScheduleService.deleteSchedule(employee.get() , unit.get());
        return "Schedule has been removed";
    }

    /**
     * method where we update a schedule of an employee
     * @param employeeId employee id
     * @param unitId unit id
     * @param startTime time when the schedule starts
     * @param endTime tiem when the schedule ends
     * @return prompt
     */
    @ShellMethod(key = "update schedule" , value = "update a scehdule plan")
    public String updateSchedule(@ShellOption(value = {"employeeId"} , help = "employee id") Long employeeId,
                                 @ShellOption(value = {"unitId"} , help = "unit id") Long unitId,
                                 @ShellOption(value = {"startTime"} , help = "start time of schedule") String startTime,
                                 @ShellOption(value = {"endTime"} , help ="end time of schedule") String endTime){
        Optional<Employee> employee = this.employeeService.findEmployeeById(employeeId);
        if(employee.isEmpty()){
            return "There is no employee with this id";
        }
        Optional<Unit> unit = this.unitService.findByUnitId(unitId);
        if(unit.isEmpty()){
            return "There is no unit with this id";
        }
        EmployeeSchedule employeeSchedule = this.employeeScheduleService.findSchedule(employee.get(),
                unit.get());
        employeeSchedule.setStartTime(LocalTime.parse(startTime));
        employeeSchedule.setEndTime(LocalTime.parse(endTime));

        return this.employeeScheduleService.save(employeeSchedule).toString();
    }
}
