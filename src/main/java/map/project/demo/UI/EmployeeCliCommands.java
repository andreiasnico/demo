package map.project.demo.UI;

import map.project.demo.Model.Employee;
import map.project.demo.Service.EmployeeService;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

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

    @ShellMethod(key = "add employee", value = "add an employee to our database")
    public String addEmployee(@ShellOption(value = {"name"}, help = "name of the employee") String name,
                              @ShellOption(value = {"state"}, help = "state of the employee") String state) {
        Employee addEmployee = new Employee();
        addEmployee.setName(name);
        return this.employeeService.save(addEmployee).toString();
    }

    @ShellMethod(key = "update employee", value = "update an employee from our database")
    public String updateEmployee(@ShellOption(value = {"employeeId"}, help = "id of the employee") Long employeeId,
                                 @ShellOption(value = {"name"}, help = "name of the employee") String name,
                                 @ShellOption(value = {"state"}, help = "state of the employee") String state) {
        Employee updateEmployee = new Employee();
        updateEmployee.setEmployeeId(employeeId);
        updateEmployee.setName(name);
        this.employeeService.updateEmployee(updateEmployee);
        return "Employee updated";
    }

    @ShellMethod(key = "delete employee", value = "delete an employee from our database")
    public String deleteEmployee(@ShellOption(value = {"employeeId"}, help = "id of the employee") Long employeeId) {
        Employee deleteEmployee = new Employee();
        deleteEmployee.setEmployeeId(employeeId);
        this.employeeService.deleteEmployee(deleteEmployee);
        return "Employee deleted";
    }

    @ShellMethod(key = "read employee", value = "read an employee from our database")

    public String readEmployee(@ShellOption(value = {"employeeId"}, help = "id of the employee") Long employeeId) {
        Employee readEmployee = new Employee();
        readEmployee.setEmployeeId(employeeId);
        this.employeeService.readEmployee(readEmployee);
        return "Employee read";
    }



}
