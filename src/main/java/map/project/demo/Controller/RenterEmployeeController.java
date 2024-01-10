package map.project.demo.Controller;

import map.project.demo.Model.Renter;
import map.project.demo.Model.RenterEmployee;
import map.project.demo.Service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import map.project.demo.Service.RenterEmployeeService;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/renter-employee")
public class RenterEmployeeController {
    @Autowired
    private RenterEmployeeService service;

    @Autowired
    private RenterService renterService;

    @GetMapping("/renter-employees")
    public String findAllRenterEmployees() {
        return service.findAllRenterEmployees().toString();
    }

    @PostMapping("add-renter-employee")
    public String addRenterEmployee(@RequestParam String name,
                                    @RequestParam String birthday,
                                    @RequestParam Long renterId){
        Optional<Renter> renter = this.renterService.findBYRenterId(renterId);
        if(renter.isEmpty()){
            return null;
        }

        RenterEmployee renterEmployee = new RenterEmployee();
        renterEmployee.setRenter(renter.get());
        renterEmployee.setName(name);
        renterEmployee.setBirthday(Date.valueOf(birthday));
        this.service.save(renterEmployee);
        return renterEmployee.toString();
    }

    @PostMapping("/delete-renter-employee")
    public String deleteRenterEmployee(@RequestParam Long renterEployeeId){
        Optional<RenterEmployee> renterEmployee = this.service.findRenterEmployeeById(renterEployeeId);

        if(renterEmployee.isEmpty()){
            return null;
        }

        this.service.removeEmployee(renterEmployee.get());
        return renterEmployee.get().toString();
    }

    @PostMapping("update-renter-employee")
    public String updateRenterEmployee(@RequestParam Long renterEmployeeId,
                                       @RequestParam String name,
                                       @RequestParam String birthday,
                                       @RequestParam Long renterId){
        Optional<RenterEmployee> renterEmployee = this.service.findRenterEmployeeById(renterEmployeeId);
        if(renterEmployee.isEmpty()){
            return null;
        }

        Optional<Renter> renter = this.renterService.findBYRenterId(renterId);
        if(renter.isEmpty()){
            return null;
        }

        renterEmployee.get().setBirthday(Date.valueOf(birthday));
        renterEmployee.get().setName(name);
        renterEmployee.get().setRenter(renter.get());

        return renterEmployee.get().toString();
    }
}
