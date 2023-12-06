package map.project.demo.UI;

import map.project.demo.Model.Renter;
import map.project.demo.Repository.RenterRepository;
import map.project.demo.Service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * class for the command line interface of the renter class
 */
@ShellComponent
public class RenterCliCommands {
    @Autowired
    private RenterService renterService;

    /**
     * method that returns all renters
     *
     * @return String of the list of renters
     */
    @ShellMethod(key = "Renter", value = "show all renters")
    public String allRenters() {
        return renterService.findAll().toString();
    }

    /**
     * method that adds a renter to the database
     *
     * @param name  firm name
     * @param email email of the renter
     * @param iban  iban of the renter
     * @return String of the added renter
     */
    @ShellMethod(key = "add renter", value = "add a renter to our database")
    public String addRenter(@ShellOption(value = {"name"}, help = "name of the renter") String name,
                            @ShellOption(value = {"email"}, help = "email of the renter") String email,
                            @ShellOption(value = {"IBAN"}, help = "IBAN of the renter") String iban) {
        Renter addRenter = new Renter();
        addRenter.setEmail(email);
        addRenter.setIBAN(iban);
        addRenter.setFirmName(name);
        return this.renterService.save(addRenter).toString();
    }

    /**
     * method that updates a renter
     *
     * @param renterId renter id
     * @param name     firm name
     * @param email    email of the renter
     * @param iban     iban of the renter
     * @return prompt that renter has been updated
     */
    @ShellMethod(key = "update renter", value = "update a renter from our database")
    public String updateRenter(@ShellOption(value = {"renterId"}, help = "id of the renter") Long renterId,
                               @ShellOption(value = {"name"}, help = "name of the renter") String name,
                               @ShellOption(value = {"email"}, help = "email of the renter") String email,
                               @ShellOption(value = {"iban"}, help = "iban of the renter") String iban) {
        Renter updateRenter = this.renterService.findBYRenterId(renterId);
        updateRenter.setRenterId(renterId);
        updateRenter.setEmail(email);
        updateRenter.setIBAN(iban);
        this.renterService.updateRenter(updateRenter);
        return "Renter updated";
    }

    /**
     * method that deletes a renter
     *
     * @param renterId renter id
     * @return prompt that renter has been deleted
     */
    @ShellMethod(key = "delete renter", value = "delete a renter from our database")
    public String deleteRenter(@ShellOption(value = {"renterId"}, help = "id of the renter") Long renterId) {
        Renter deleteRenter = new Renter();
        deleteRenter.setRenterId(renterId);
        this.renterService.deleteRenter(deleteRenter);
        return "Renter deleted";
    }


}
