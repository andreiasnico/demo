package map.project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import map.project.demo.Service.RenterEmployeeService;
@RestController
@RequestMapping("localhost:8080")
public class RenterEmployeeController {
    @Autowired
    private RenterEmployeeService service;

    @RequestMapping("/renteremployees")
    public String findAllRenterEmployees() {
        return service.findAllRenterEmployees().toString();


    }
}
