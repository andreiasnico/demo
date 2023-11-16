package map.project.demo.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import map.project.demo.Model.Counter;
import map.project.demo.Service.CounterService;

@ShellComponent
public class CounterCliCommands {
    @Autowired
    private CounterService counterService;

    @ShellMethod(key = "Counter", value = "show all counters")
    public String allCounters() {
        return counterService.findAll().toString();
    }


}
