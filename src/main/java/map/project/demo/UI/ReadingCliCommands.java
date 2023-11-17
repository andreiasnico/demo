package map.project.demo.UI;

import map.project.demo.Model.BillStatus;
import map.project.demo.Model.Reading;
import map.project.demo.Service.BillService;
import map.project.demo.Service.CounterService;
import map.project.demo.Service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ReadingCliCommands {
    @Autowired
    private ReadingService readingService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private BillService billService;

    @ShellMethod(key = "Reading", value = "show all readings")
    public String allReadings() {
        return readingService.findAll().toString();
    }


    @ShellMethod(key = "add reading" , value = "add a new reading")
    public String addReading(@ShellOption(value = {"readingId"}, help = "id of the reading") Long readingId,
                             @ShellOption(value = {"billId"}, help = "id of the bill") Long billId,
                             @ShellOption(value = {"counterId"}, help = "id of the bill") Long counterId
                             ){
        Reading reading = new Reading();
        reading.setCounter(this.counterService.f);
    }


}
