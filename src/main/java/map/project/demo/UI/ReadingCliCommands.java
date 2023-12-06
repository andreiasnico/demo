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

/**
 * class for the command line interface of the reading class
 */
@ShellComponent
public class ReadingCliCommands {
    @Autowired
    private ReadingService readingService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private BillService billService;

    /**
     * method that returns all the readings
     *
     * @return String of the list of all readings
     */
    @ShellMethod(key = "Reading", value = "show all readings")
    public String allReadings() {
        return readingService.findAll().toString();
    }

    /**
     * method that adds a new reading
     *
     * @param volume    volume of the reading
     * @param billId    bill id
     * @param counterId counter id
     * @return String of the added reading
     */
    @ShellMethod(key = "add reading", value = "add a new reading")
    public String addReading(@ShellOption(value = {"volume"}, help = "volume of the counter") Long volume,
                             @ShellOption(value = {"billId"}, help = "id of the bill") Long billId,
                             @ShellOption(value = {"counterId"}, help = "id of the bill") Long counterId
    ) {
        Reading reading = new Reading();
        reading.setVolumeReading(volume);
        reading.setCounter(this.counterService.findbyCounterId(counterId));
        reading.setBill(this.billService.findByBillId(billId).get());
        return this.readingService.save(reading).toString();
    }

    /**
     * method that deletes a reading
     *
     * @param readingId reading id
     * @return Prompt that the reading has been deleted
     */
    @ShellMethod(key = "delete reading", value = "delete reading by id")
    public String deleteReading(@ShellOption(value = {"readingId"}, help = "id of the reading") Long readingId) {
        Reading reading = this.readingService.findReadingById(readingId);
        this.readingService.delete(reading);
        return "Reading has been removed";
    }

    /**
     * method that updates a reading
     *
     * @param readingId reading id
     * @param volume    volume of the reading
     * @param counterId counter id
     * @param billId    bill id
     * @return String of the updated reading
     */
    @ShellMethod(key = "update reading", value = "update reading based on id")
    public String updateReading(@ShellOption(value = {"readingId"}, help = "id of the reading") Long readingId,
                                @ShellOption(value = {"volume"}, help = "volume of the reading") Long volume,
                                @ShellOption(value = {"counter"}, help = "counter id") Long counterId,
                                @ShellOption(value = {"bill"}, help = "billId") Long billId) {
        Reading reading = this.readingService.findReadingById(readingId);
        reading.setVolumeReading(volume);
        reading.setBill(this.billService.findByBillId(billId).get());
        reading.setCounter(this.counterService.findbyCounterId(counterId));
        return this.readingService.save(reading).toString();
    }

}
