package map.project.demo.UI;

import map.project.demo.Model.RenterEmployee;
import map.project.demo.Service.RenterEmployeeService;
import map.project.demo.Service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.Date;

/**
 * class that manages the renter employee command line interface
 */
@ShellComponent
public class RenterEmployeeCommands {
    @Autowired
    private RenterService renterService;
    @Autowired
    private RenterEmployeeService renterEmployeeService;

    /**
     * method that returns all renter employees
     *
     * @return String of the list of all renter employees
     */
    @ShellMethod(key = "renter employees", value = "find all renter employees")
    public String findAll() {
        return this.renterEmployeeService.findAllRenterEmployees().toString();
    }

    /**
     * method that adds a new renter employee
     *
     * @param name     name of the employee
     * @param birthday birthday of the employee
     * @param renterId renter id
     * @return String of the added employee
     */
    @ShellMethod(key = "add renter employee", value = "add a new renter employee")
    public String addRenterEmployee(@ShellOption(value = {"name"}, help = "name of the employee") String name,
                                    @ShellOption(value = {"birthday"}, help = "date of the birthday format yyyy-mm-dd") String birthday,
                                    @ShellOption(value = {"renterId"}, help = "id of the reter") Long renterId) {
        RenterEmployee employee = new RenterEmployee();
        employee.setRenter(this.renterService.findBYRenterId(renterId));
        employee.setBirthday(Date.valueOf(birthday));
        employee.setName(name);
        return this.renterEmployeeService.save(employee).toString();
    }

    /**
     * method that deletes a renter employee
     *
     * @param employeeId id of the renter employee
     * @return Prompt that the employee is deleted
     */
    @ShellMethod(key = "delete renter employee", value = "delete a renter employee")
    private String deleteRenterEmployee(@ShellOption(value = {"employeeId"}, help = "id of the renter employee") Long employeeId) {
        this.renterEmployeeService.removeEmployee(this.renterEmployeeService.findRenterEmployeeById(employeeId));
        return "Employee deleted";
    }

    /**
     * method that updates a renter employee
     *
     * @param renterEmployeeId renter employee id
     * @param name             employee name
     * @param date             emaployee birthday
     * @param renterId         renter id
     * @return String of the updated Employee
     */
    @ShellMethod(key = "update renter employee ", value = "update a renter employee")
    private String updateRenterEmployee(@ShellOption(value = {"employeeId"}, help = "id of the renter employee") Long renterEmployeeId,
                                        @ShellOption(value = {"name"}, help = "name of the renter employee") String name,
                                        @ShellOption(value = {"birthday"}, help = "birthday of the renter employee") String date,
                                        @ShellOption(value = {"renterId"}, help = "id of the renter") Long renterId) {
        RenterEmployee renterEmployee = this.renterEmployeeService.findRenterEmployeeById(renterEmployeeId);
        renterEmployee.setName(name);
        renterEmployee.setBirthday(Date.valueOf(date));
        renterEmployee.setRenter(this.renterService.findBYRenterId(renterId));
        return this.renterEmployeeService.save(renterEmployee).toString();
    }
}
