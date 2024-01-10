package map.project.demo.Controller;

import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Renter;
import map.project.demo.Model.dto.RenterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import map.project.demo.Service.RenterService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/renter")
public class RenterController {
    @Autowired
    private RenterService service;

    @GetMapping("/find-all-renters")
    public List<RenterDto> findAllRenters() {
        List<Renter> renters = this.service.findAllRenters();
        return renters.stream().map(renter -> (RenterDto) AdapterFacade.adaptToDto(renter, Renter.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/add-renter")
    public RenterDto addRenter(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String iban){
        Renter renter = new Renter();
        renter.setFirmName(name);
        renter.setEmail(email);
        renter.setIBAN(iban);

        this.service.addRenter(renter);
        return (RenterDto) AdapterFacade.adaptToDto(renter , Renter.class);
    }

    @PostMapping("/delete-renter")
    public RenterDto updateRenter(@RequestParam Long renterId,
                                  @RequestParam String name,
                                  @RequestParam String email,
                                  @RequestParam String iban){
        Optional<Renter> renter = this.service.findBYRenterId(renterId);
        if(renter.isEmpty()){
            return null;
        }

        renter.get().setIBAN(iban);
        renter.get().setEmail(email);
        renter.get().setFirmName(name);

        this.service.addRenter(renter.get());
        return (RenterDto) AdapterFacade.adaptToDto(renter , Renter.class);
    }
}
