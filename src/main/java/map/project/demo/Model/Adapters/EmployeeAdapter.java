package map.project.demo.Model.Adapters;

import map.project.demo.Model.Employee;
import map.project.demo.Model.dto.EmployeeDto;

public class EmployeeAdapter implements Adapter<Employee, EmployeeDto> {
    @Override
    public EmployeeDto transformToDto(Employee concreteObject) {
        return new EmployeeDto(concreteObject.getEmployeeId(),
                concreteObject.getName(),
                concreteObject.getSalary(),
                concreteObject.getState()
                );
    }
}
