package map.project.demo.UI;

import map.project.demo.Model.Employee;
import map.project.demo.Model.States.State;
import map.project.demo.Model.Titles;
import map.project.demo.Service.EmployeeService;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;

/**
 * class for the employee class command line interface
 */
@ShellComponent
public class EmployeeCliCommands {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UnitService unitService;

    /**
     * method that shows all employees
     *
     * @return list of employees
     */
    @ShellMethod(key = "employee", value = "show all employees")
    public String allEmployees() {
        return employeeService.getEmployeesUnderWorked().toString();
    }

    /**
     * method that adds a new employee to the database
     *
     * @param name   name of the employee
     * @param state  state of employee
     * @param title  title of the employee
     * @param salary salary of the employee
     * @return String of the added employee
     */
    @ShellMethod(key = "add employee", value = "add an employee to our database")
    public String addEmployee(@ShellOption(value = {"name"}, help = "name of the employee") String name,
                              @ShellOption(value = {"state"}, help = "state of the employee") String state,
                              @ShellOption(value = {"title"}, help = "title of the employee") String title,
                              @ShellOption(value = {"salary"}, help = "salary of the employee") Long salary) {
        Employee addEmployee = new Employee();
        addEmployee.setName(name);
        addEmployee.setSalary(salary);
        addEmployee.setTitle(Titles.valueOf(title));
        addEmployee.setState(State.valueOf(state));
        return this.employeeService.save(addEmployee).toString();
    }

    /**
     * method that updates an employee
     *
     * @param employeeId employee id
     * @param name       name of the employee
     * @param state      state of the employee
     * @param salary     salary of the employee
     * @return prompt that the employee has been added and the updated employee
     */
    @ShellMethod(key = "update employee", value = "update an employee from our database")
    public String updateEmployee(@ShellOption(value = {"employeeId"}, help = "id of the employee") Long employeeId,
                                 @ShellOption(value = {"name"}, help = "name of the employee") String name,
                                 @ShellOption(value = {"state"}, help = "state of the employee") String state,
                                 @ShellOption(value = {"salary"}, help = "salary of the employee") Long salary) {
        Optional<Employee> updateEmployee = this.employeeService.findEmployeeById(employeeId);
        if(updateEmployee.isEmpty()){
            return "There is no employee with this id";
        }
        updateEmployee.get().setEmployeeId(employeeId);
        updateEmployee.get().setName(name);
        updateEmployee.get().setSalary(salary);
        updateEmployee.get().setState(State.valueOf(state));
        this.employeeService.updateEmployee(updateEmployee.get());
        return "Employee updated " + updateEmployee.toString();
    }

    /**
     * method that deletes an employee
     *
     * @param employeeId employee id
     * @return prompt that the employee has been deleted
     */
    @ShellMethod(key = "delete employee", value = "delete an employee from our database")
    public String deleteEmployee(@ShellOption(value = {"employeeId"}, help = "id of the employee") Long employeeId) {
        Employee deleteEmployee = new Employee();
        deleteEmployee.setEmployeeId(employeeId);
        this.employeeService.deleteEmployee(deleteEmployee);
        return "Employee deleted";
    }

    /**
     * method that gets the info about an employee
     *
     * @param employeeId employee id
     * @return String of the employee information
     */
    @ShellMethod(key = "employee info", value = "read an employee from our database")
    public String readEmployee(@ShellOption(value = {"employeeId"}, help = "id of the employee") Long employeeId) {
        Employee readEmployee = new Employee();
        readEmployee.setEmployeeId(employeeId);
        return this.employeeService.readEmployee(readEmployee.getEmployeeId()).toString();

    }
}
