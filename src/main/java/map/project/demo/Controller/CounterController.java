package map.project.demo.Controller;

import map.project.demo.Service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("localhost:8080")
public class CounterController {
    @Autowired
    private CounterService service;

    @RequestMapping("/counters")
    public String findAllCounters() {
        return service.findAllCounters().toString();


    }

}
