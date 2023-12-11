package map.project.demo.UI;

import map.project.demo.Model.Bill;
import map.project.demo.Model.Counter;
import map.project.demo.Model.Reading;
import map.project.demo.Service.BillService;
import map.project.demo.Service.CounterService;
import map.project.demo.Service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;

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
        Optional<Counter> counter = this.counterService.findbyCounterId(counterId);
        if (counter.isEmpty()) {
            return "There is no counter with this id";
        }
        Optional<Bill> bill = this.billService.findByBillId(billId);
        if(bill.isEmpty()){
            return "There is no bill with this id";
        }
        Reading reading = new Reading();
        reading.setVolumeReading(volume);
        reading.setCounter(counter.get());
        reading.setBill(bill.get());
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
        Optional<Reading> reading = this.readingService.findReadingById(readingId);
        if(reading.isEmpty()){
            return "There is no reading with this id";
        }
        this.readingService.delete(reading.get());
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
        Optional<Reading> reading = this.readingService.findReadingById(readingId);
        if(reading.isEmpty()){
            return "There is no reading with this id";
        }
        Optional<Counter> counter = this.counterService.findbyCounterId(counterId);
        if(counter.isEmpty()){
            return "There is no counter with this id";
        }
        reading.get().setVolumeReading(volume);
        reading.get().setBill(this.billService.findByBillId(billId).get());
        reading.get().setCounter(counter.get());
        return this.readingService.save(reading.get()).toString();
    }

}
