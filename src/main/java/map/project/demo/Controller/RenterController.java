package map.project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import map.project.demo.Service.RenterService;
@RestController
@RequestMapping("localhost:8080")
public class RenterController {
    @Autowired
    private RenterService service;

    @RequestMapping("/renters")
    public String findAllRenters() {
        return service.findAllRenters().toString();


    }

}
