package map.project.demo.UI;

import map.project.demo.Model.Employee;
import map.project.demo.Model.States.FullTimeEmployee;
import map.project.demo.Model.States.PartTimeEmployee;
import map.project.demo.Model.States.State;
import map.project.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class EmployeeCliCommands {
    @Autowired
    private EmployeeRepository employeeRepository;

    @ShellMethod(key = "employee",value = "show all employees")
    public String allEmployees(){
        return employeeRepository.findAll().toString();
    }
    @ShellMethod(key = "add employee",value = "add an employee to our database")
    public String addEmployee(@ShellOption(value = {"name"},help = "name of the employee") String name,
                              @ShellOption(value = {"salary"},help = "salary of the employee") Long salary,
                              @ShellOption(value = {"state"},help = "state of the employee") State state){
        Employee addEmployee = new Employee();
        addEmployee.setName(name);
        addEmployee.setSalary(salary);
        addEmployee.setState(state);
        return this.employeeRepository.save(addEmployee).toString();
    }
    @ShellMethod(key = "update employee",value = "update an employee from our database")
    public String updateEmployee(@ShellOption(value = {"employeeId"},help = "id of the employee") Long employeeId,
                                 @ShellOption(value = {"name"},help = "name of the employee") String name,
                                 @ShellOption(value = {"salary"},help = "salary of the employee") Long salary,
                                 @ShellOption(value = {"state"},help = "state of the employee") State state){
        Employee updateEmployee = this.employeeRepository.findById(employeeId).get();
        updateEmployee.setName(name);
        updateEmployee.setSalary(salary);
        updateEmployee.setState(state);
        return this.employeeRepository.save(updateEmployee).toString();
    }
    @ShellMethod(key = "delete employee",value = "delete an employee from our database")
    public String deleteEmployee(@ShellOption(value = {"employeeId"},help = "id of the employee") Long employeeId){
        this.employeeRepository.deleteById(employeeId);
        return "Employee deleted";
    }
    @ShellMethod(key = "underworked employees",value = "show all underworked employees")
    public String underWorkedEmployees(){
        String underWorkedEmployees = "";
        for (Employee employee: this.employeeRepository.findAll()
             ) {
            if(employee.getState() == State.FullTime){
                FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();
                if(!fullTimeEmployee.handle(employee)){
                    underWorkedEmployees += employee.toString();
                }
            }
            else{
                PartTimeEmployee partTimeEmployee = new PartTimeEmployee();
                if(!partTimeEmployee.handle(employee)){
                    underWorkedEmployees += employee.toString();
                }
            }
        }
        return underWorkedEmployees;
    }
}
