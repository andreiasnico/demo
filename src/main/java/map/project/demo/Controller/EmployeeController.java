package map.project.demo.Controller;


import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Employee;
import map.project.demo.Model.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import map.project.demo.Service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/find-all-employees")
    public List<EmployeeDto> findAllEmployees() {
        List<Employee> employees = service.findAllEmployees();

        return employees.stream().map(employee -> (EmployeeDto) AdapterFacade.adaptToDto(employee, Employee.class))
                .collect(Collectors.toList());
    }


}
