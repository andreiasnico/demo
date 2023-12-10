package map.project.demo.Controller;

import map.project.demo.Service.EmployeeScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("localhost:8080")
public class EmployeeScheduleController {
    @Autowired
    private EmployeeScheduleService service;

    @RequestMapping("/employeeschedules")
    public String findAllEmployeeSchedules() {
        return service.findAllEmployeeSchedules().toString();


    }
}
