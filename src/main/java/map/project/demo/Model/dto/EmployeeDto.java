package map.project.demo.Model.dto;

import map.project.demo.Model.States.State;

public class EmployeeDto {

    private Long employeeId;

    private String name;

    private Long salary;

    private State state;

    public EmployeeDto(Long employeeId, String name, Long salary, State state) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
        this.state = state;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
