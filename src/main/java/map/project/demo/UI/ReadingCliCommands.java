package map.project.demo.UI;

import map.project.demo.Model.Reading;
import map.project.demo.Service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ReadingCliCommands {
    @Autowired
    private ReadingService readingService;

    @ShellMethod(key = "Reading", value = "show all readings")
    public String allReadings() {
        return readingService.findAll().toString();
    }


}
