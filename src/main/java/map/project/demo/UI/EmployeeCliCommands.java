package map.project.demo.UI;

import map.project.demo.Model.Employee;
import map.project.demo.Service.EmployeeService;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class EmployeeCliCommands {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UnitService employeeRepository;

    @ShellMethod(key = "employee",value = "show all employees")
    public String allEmployees(){
        return employeeRepository.findAll().toString();
    }


}
