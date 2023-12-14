package map.project.demo.Controller;

import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Renter;
import map.project.demo.Model.dto.RenterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import map.project.demo.Service.RenterService;

import java.util.List;
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

}
