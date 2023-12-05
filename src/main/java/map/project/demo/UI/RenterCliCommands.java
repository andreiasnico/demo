package map.project.demo.UI;

import map.project.demo.Model.Renter;
import map.project.demo.Repository.RenterRepository;
import map.project.demo.Service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class RenterCliCommands {
    @Autowired
    private RenterService renterService;

    @ShellMethod(key = "Renter",value = "show all renters")
    public String allRenters(){return renterService.findAll().toString();
    }

    @ShellMethod(key = "add renter", value = "add a renter to our database")
    public String addRenter(@ShellOption(value = {"name"}, help = "name of the renter") String name,
                            @ShellOption(value = {"address"}, help = "address of the renter") String address,
                            @ShellOption(value = {"phoneNumber"}, help = "phone number of the renter") String phoneNumber,
                            @ShellOption(value = {"email"}, help = "email of the renter") String email) {
        Renter addRenter = new Renter();
        addRenter.setEmail(email);
        return this.renterService.save(addRenter).toString();
    }

    @ShellMethod(key = "update renter", value = "update a renter from our database")
    public String updateRenter(@ShellOption(value = {"renterId"}, help = "id of the renter") Long renterId,
                               @ShellOption(value = {"name"}, help = "name of the renter") String name,
                               @ShellOption(value = {"address"}, help = "address of the renter") String address,
                               @ShellOption(value = {"phoneNumber"}, help = "phone number of the renter") String phoneNumber,
                               @ShellOption(value = {"email"}, help = "email of the renter") String email) {
        Renter updateRenter = new Renter();
        updateRenter.setRenterId(renterId);
        updateRenter.setEmail(email);
        this.renterService.updateRenter(updateRenter);
        return "Renter updated";
    }

    @ShellMethod(key = "delete renter", value = "delete a renter from our database")
    public String deleteRenter(@ShellOption(value = {"renterId"}, help = "id of the renter") Long renterId) {
        Renter deleteRenter = new Renter();
        deleteRenter.setRenterId(renterId);
        this.renterService.deleteRenter(deleteRenter);
        return "Renter deleted";
    }


}
