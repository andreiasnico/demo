package map.project.demo.UI;

import map.project.demo.Model.Renter;
import map.project.demo.Repository.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class RenterCliCommands {
    @Autowired
    private RenterRepository renterRepository;

    @ShellMethod(key = "Renter",value = "show all renters")
    public String allRenters(){return renterRepository.findAll().toString();
    }

}
