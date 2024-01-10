package map.project.demo.Controller;

import map.project.demo.Model.Employee;
import map.project.demo.Model.EmployeeSchedule;
import map.project.demo.Model.ScheduleKey;
import map.project.demo.Model.Unit;
import map.project.demo.Service.EmployeeScheduleService;
import map.project.demo.Service.EmployeeService;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Optional;

@RestController
@RequestMapping("/employee-schedule")
public class EmployeeScheduleController {
    @Autowired
    private EmployeeScheduleService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UnitService unitService;

    @GetMapping("/employee-schedules")
    public String findAllEmployeeSchedules() {
        return service.findAllEmployeeSchedules().toString();
    }

    @PostMapping("/add-schedule")
    public String addSchedule(@RequestParam Long employeeId,
                              @RequestParam Long unitId,
                              @RequestParam String startTime,
                              @RequestParam String endTime) {
        EmployeeSchedule employeeSchedule = new EmployeeSchedule();
        Optional<Employee> employee = this.employeeService.findEmployeeById(employeeId);
        if (employee.isEmpty()) {
            return null;
        }
        employeeSchedule.setEmployee(employee.get());

        Optional<Unit> unit = this.unitService.findByUnitId(unitId);
        if (unit.isEmpty()) {
            return null;
        }
        employeeSchedule.setUnit(unit.get());

        employeeSchedule.setStartTime(LocalTime.parse(startTime));
        employeeSchedule.setEndTime(LocalTime.parse(endTime));
        this.service.addSchedule(employeeSchedule);
        return employeeSchedule.toString();
    }

    @PostMapping("/delete-schedule")
    public String deleteSchedule(@RequestParam Long employeeId,
                                 @RequestParam Long unitId) {
        Optional<Employee> employee = this.employeeService.findEmployeeById(employeeId);
        if (employee.isEmpty()) {
            return null;
        }

        Optional<Unit> unit = this.unitService.findByUnitId(unitId);
        if (unit.isEmpty()) {
            return null;
        }

        this.service.deleteSchedule(employee.get(), unit.get());
        return "schedule deleted successfully";
    }

    @PostMapping("/update-schedule")
    public String updateSchedule(@RequestParam Long employeeId,
                                 @RequestParam Long unitId,
                                 @RequestParam String startTime,
                                 @RequestParam String endime) {
        Optional<Employee> employee = this.employeeService.findEmployeeById(employeeId);
        if(employee.isEmpty()){
            return null;
        }

        Optional<Unit> unit = this.unitService.findByUnitId(unitId);
        if(unit.isEmpty()){
            return null;
        }

        EmployeeSchedule employeeSchedule = this.service.findSchedule(employee.get() , unit.get());
        employeeSchedule.setEndTime(LocalTime.parse(endime));
        employeeSchedule.setStartTime(LocalTime.parse(startTime));

        this.service.addSchedule(employeeSchedule);
        return employeeSchedule.toString();
    }
}
