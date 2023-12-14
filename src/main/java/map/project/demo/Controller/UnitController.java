package map.project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import map.project.demo.Service.UnitService;
@RestController
@RequestMapping("localhost:8080")
public class UnitController {
    @Autowired
    private UnitService service;

    @GetMapping("/units")
    public String findAllUnits() {
        return service.findAllUnits().toString();


    }

}
