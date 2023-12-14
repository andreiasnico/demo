package map.project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import map.project.demo.Service.ReadingService;
@RestController
@RequestMapping("localhost:8080")
public class ReadingController {
    @Autowired
    private ReadingService service;

    @GetMapping("/readings")
    public String findAllReadings() {
        return service.findAllReadings().toString();


    }
}
