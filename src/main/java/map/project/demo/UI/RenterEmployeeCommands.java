package map.project.demo.UI;

import map.project.demo.Model.Renter;
import map.project.demo.Model.RenterEmployee;
import map.project.demo.Service.RenterEmployeeService;
import map.project.demo.Service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.Date;
import java.util.Optional;

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
        Optional<Renter> renter = this.renterService.findBYRenterId(renterId);
        if(renter.isEmpty()){
            return "There is no renter with this id";
        }
        employee.setRenter(renter.get());
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
        Optional<RenterEmployee> renterEmployee = this.renterEmployeeService.findRenterEmployeeById(employeeId);
        if(renterEmployee.isEmpty()){
            return "There is no renter employee with this id";
        }
        this.renterEmployeeService.removeEmployee(renterEmployee.get());
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

        Optional<RenterEmployee> renterEmployee = this.renterEmployeeService.findRenterEmployeeById(renterEmployeeId);
        if(renterEmployee.isEmpty()){
            return "There is no renter employee with this id";
        }
        Optional<Renter> renter = this.renterService.findBYRenterId(renterId);
        if(renter.isEmpty()){
            return "There is no renter with this id";
        }
        renterEmployee.get().setName(name);
        renterEmployee.get().setBirthday(Date.valueOf(date));
        renterEmployee.get().setRenter(renter.get());
        return this.renterEmployeeService.save(renterEmployee.get()).toString();
    }
}
