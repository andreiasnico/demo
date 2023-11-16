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
}
