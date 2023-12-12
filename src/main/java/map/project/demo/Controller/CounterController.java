package map.project.demo.Controller;

import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Counter;
import map.project.demo.Model.dto.CounterDto;
import map.project.demo.Service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/counter")
public class CounterController {
    @Autowired
    private CounterService service;

    @RequestMapping("/find-all-counters")
    public List<CounterDto> findAllCounters() {
        List<Counter> counters = service.findAllCounters();
        return counters.stream().map(counter -> (CounterDto) AdapterFacade.adaptToDto(counter, Counter.class))
                .collect(Collectors.toList());
    }

}
