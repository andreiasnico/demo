package map.project.demo.Service;

import map.project.demo.Model.Employee;
import map.project.demo.Model.States.FullTimeEmployee;
import map.project.demo.Model.States.PartTimeEmployee;
import map.project.demo.Model.States.State;
import map.project.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployeesUnderWorked(){
        List<Employee> underWorkedEmployees = new ArrayList<>();
        for (Employee employee: this.employeeRepository.findAll()
        ) {
            if(employee.getState() == State.FullTime){
                FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();
                if(!fullTimeEmployee.handle(employee)){
                    underWorkedEmployees.add(employee);
                }
            }
            else{
                PartTimeEmployee partTimeEmployee = new PartTimeEmployee();
                if(!partTimeEmployee.handle(employee)){
                    underWorkedEmployees.add(employee);
                }
            }
        }
        return underWorkedEmployees;
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.findById(employee.getEmployeeId()).get();
        updatedEmployee.setState(employee.getState());
        employeeRepository.save(updatedEmployee);
    }

    public void readEmployee(Employee employee) {
        employeeRepository.findById(employee.getEmployeeId());
    }

    public Object save(Employee addEmployee) {
        return null;
    }
}
