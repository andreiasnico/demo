package map.project.demo.UI;

import map.project.demo.Model.CounterTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import map.project.demo.Model.Counter;
import map.project.demo.Service.CounterService;
import org.springframework.shell.standard.ShellOption;

import java.sql.Date;

/**
 * class that manages the counter command line interface
 */
@ShellComponent
public class CounterCliCommands {
    @Autowired
    private CounterService counterService;

    /**
     * method that lists all the counters
     *
     * @return String of the list of counters
     */
    @ShellMethod(key = "Counter", value = "show all counters")
    public String allCounters() {
        return counterService.findAll().toString();
    }

    /**
     * method that adds a new counter to the database
     *
     * @param date      checking date
     * @param type      type of Counter
     * @param unitPrice price per unit
     * @return String of the added counter
     */
    @ShellMethod(key = "add Counter", value = "create a counter")
    public String addCounter(@ShellOption(value = {"checkDate"}, help = "enter the date when the counter needs checking form yyyy-mm-dd") String date,
                             @ShellOption(value = {"type"}, help = "counter type") String type,
                             @ShellOption(value = {"unitPrice"}, help = "id of the bill") Long unitPrice
    ) {
        Counter counter = new Counter();
        counter.setCheckingDate(Date.valueOf(date));
        counter.setCounterTypes(CounterTypes.valueOf(type));
        counter.setPricePerUnit(unitPrice);
        return this.counterService.save(counter).toString();
    }

    /**
     * method that deletes a counter
     *
     * @param counterId counter Id
     * @return Prompt that the counter has been deleted
     */
    @ShellMethod(key = "delete counter", value = "delete counter by id")
    public String deleteCounter(@ShellOption(value = {"counterId"}, help = "id of the counter") Long counterId) {
        Counter counter = this.counterService.findbyCounterId(counterId);
        this.counterService.delete(counter);
        return "Counter has been deleted";
    }

    /**
     * method that updates a counter
     *
     * @param counterId id of the counter
     * @param unitPrice price per unit for the counter
     * @return Prompt that the counter has been updated
     * noted that we do not update the counter type
     */
    @ShellMethod(key = "update counter", value = "update counter by id")
    public String updateCounter(@ShellOption(value = {"counterId"}, help = "id of the counter") Long counterId,
                                @ShellOption(value = {"unitPrice"}, help = "id of the bill") Long unitPrice,
                                @ShellOption(value = {"date"}, help = "the checking date that we need") String date) {
        Counter counter = this.counterService.findbyCounterId(counterId);
        counter.setPricePerUnit(unitPrice);
        counter.setCheckingDate(Date.valueOf(date));
        this.counterService.updateCounter(counter);
        return "Counter has been updated";
    }

    /**
     * method that gets the info for a counter
     *
     * @param counterId counter id
     * @return String of the counter
     */
    @ShellMethod(key = "counter info", value = "read counter by id")
    public String readCounter(@ShellOption(value = {"counterId"}, help = "id of the counter") Long counterId) {
        return this.counterService.findbyCounterId(counterId).toString();
    }

}
