package map.project.demo.UI;

import map.project.demo.Model.CounterTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import map.project.demo.Model.Counter;
import map.project.demo.Service.CounterService;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CounterCliCommands {
    @Autowired
    private CounterService counterService;

    @ShellMethod(key = "Counter", value = "show all counters")
    public String allCounters() {
        return counterService.findAll().toString();
    }

    @ShellMethod(key = "add Counter" , value = "create a counter")
    public String addCounter(@ShellOption(value = {"counterId"}, help = "id of the bill") Long counterId,
                             @ShellOption(value = {"type"}, help = "counter type") String type,
                             @ShellOption(value = {"unitPrice"}, help = "id of the bill") Long unitPrice
                             ){
        Counter counter = new Counter();
        counter.setCounterId(counterId);
        counter.setCounterTypes(CounterTypes.valueOf(type));
        counter.setPricePerUnit(unitPrice);
        this.counterService.save(counter);
        return "Counter has been saved";
    }

}
