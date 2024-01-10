package map.project.demo.Controller;

import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Counter;
import map.project.demo.Model.CounterTypes;
import map.project.demo.Model.dto.CounterDto;
import map.project.demo.Service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/counter")
public class CounterController {
    @Autowired
    private CounterService service;

    @GetMapping("/find-all-counters")
    public List<CounterDto> findAllCounters() {
        List<Counter> counters = service.findAllCounters();
        return counters.stream().map(counter -> (CounterDto) AdapterFacade.adaptToDto(counter, Counter.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/add-counter")
    public CounterDto addCounter(@RequestParam String checkDate,
                                 @RequestParam String counterType,
                                 @RequestParam Long unitPrice){
        Counter counter = new Counter();
        counter.setCounterTypes(CounterTypes.valueOf(counterType));
        counter.setPricePerUnit(unitPrice);
        counter.setCheckingDate(Date.valueOf(checkDate));

        this.service.addCounter(counter);
        return (CounterDto) AdapterFacade.adaptToDto(counter , Counter.class);
    }

    @PostMapping("/delete-counter")
    public CounterDto deleteCounter(@RequestParam Long counterId){
        Optional<Counter> counter = this.service.findbyCounterId(counterId);
        if(counter.isEmpty()){
            return null;
        }
        else{
            this.service.delete(counter.get());
            return (CounterDto) AdapterFacade.adaptToDto(counter.get() , Counter.class);
        }
    }

    @PostMapping("/update-counter")
    public CounterDto updateCounter(@RequestParam Long counterId,
                                    @RequestParam Long unitPrice,
                                    @RequestParam String checkingDate){
        Optional<Counter> counter = this.service.findbyCounterId(counterId);
        if(counter.isEmpty()){
            return null;
        }
        else{
            counter.get().setCheckingDate(Date.valueOf(checkingDate));
            counter.get().setPricePerUnit(unitPrice);
            this.service.addCounter(counter.get());
            return (CounterDto) AdapterFacade.adaptToDto(counter.get() , Counter.class);
        }
    }
}
