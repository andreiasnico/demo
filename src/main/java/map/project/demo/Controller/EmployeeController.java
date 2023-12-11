package map.project.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import map.project.demo.Service.EmployeeService;



@RestController
@RequestMapping("localhost:8080")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/employees")
    public String findAllEmployees() {
        return service.findAllEmployees().toString();
    }





}
