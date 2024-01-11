package map.project.demo.Controller;


import map.project.demo.Model.Adapters.AdapterProxy;
import map.project.demo.Model.Employee;
import map.project.demo.Model.States.State;
import map.project.demo.Model.Titles;
import map.project.demo.Model.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import map.project.demo.Service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/find-all-employees")
    public List<EmployeeDto> findAllEmployees() {
        List<Employee> employees = service.findAllEmployees();
        return employees.stream().map(employee -> (EmployeeDto) AdapterProxy.adaptToDto(employee, Employee.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/add-employee")
    public EmployeeDto addEmployee(@RequestParam String name,
                                   @RequestParam String state,
                                   @RequestParam String title,
                                   @RequestParam Long salary) {
        Employee employee = new Employee();

        employee.setName(name);
        employee.setTitle(Titles.valueOf(title));
        employee.setState(State.valueOf(state));
        employee.setSalary(salary);
        this.service.addEmployee(employee);
        return (EmployeeDto) AdapterProxy.adaptToDto(employee, Employee.class);
    }

    @PostMapping("/delete-employee")
    public EmployeeDto deleteEmployee(@RequestParam Long employeeId) {
        Optional<Employee> employee = this.service.findEmployeeById(employeeId);

        if (employee.isEmpty()) {
            return null;
        }

        this.service.deleteEmployee(employee.get());

        return (EmployeeDto) AdapterProxy.adaptToDto(employee.get(), Employee.class);
    }

    @PostMapping("update-employee")
    public EmployeeDto updateEmployee(@RequestParam Long employeeId,
                                      @RequestParam String name,
                                      @RequestParam String state,
                                      @RequestParam String title,
                                      @RequestParam Long salary){
        Optional<Employee> employee = this.service.findEmployeeById(employeeId);
        if(employee.isEmpty()){
            return null;
        }

        employee.get().setSalary(salary);
        employee.get().setState(State.valueOf(state));
        employee.get().setTitle(Titles.valueOf(title));
        employee.get().setName(name);
        this.service.addEmployee(employee.get());
        return (EmployeeDto) AdapterProxy.adaptToDto(employee.get() , Employee.class);
    }
}
