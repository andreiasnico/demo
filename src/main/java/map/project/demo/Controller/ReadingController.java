package map.project.demo.Controller;

import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Bill;
import map.project.demo.Model.Counter;
import map.project.demo.Model.Reading;
import map.project.demo.Model.Renter;
import map.project.demo.Service.BillService;
import map.project.demo.Service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import map.project.demo.Service.ReadingService;

import java.util.Optional;

@RestController
@RequestMapping("/reading")
public class ReadingController {
    @Autowired
    private ReadingService service;

    @Autowired
    private BillService billService;

    @Autowired
    private CounterService counterService;

    @GetMapping("/readings")
    public String findAllReadings() {
        return service.findAllReadings().toString();
    }

    @PostMapping("/add-reading")
    public String addReading(@RequestParam Long volume,
                             @RequestParam Long billId,
                             @RequestParam Long counterId){
        Optional<Bill> bill = this.billService.findByBillId(billId);
        if(bill.isEmpty()){
            return null;
        }

        Optional<Counter> counter = this.counterService.findbyCounterId(counterId);
        if(counter.isEmpty()){
            return null;
        }

        Reading reading = new Reading();
        reading.setBill(bill.get());
        reading.setVolumeReading(volume);
        reading.setCounter(counter.get());
        return  reading.toString();
    }

    @PostMapping("/delete-reading")
    public String deleteReading(@RequestParam Long readingId){
        Optional<Reading> reading = this.service.findReadingById(readingId);
        if (reading.isEmpty()){
            return null;
        }

        this.service.delete(reading.get());
        return reading.get().toString();
    }

    @PostMapping("/update-reading")
    public String updateReading(@RequestParam Long readingId,
                                @RequestParam Long volume,
                                @RequestParam Long billId,
                                @RequestParam Long counterId){
        Optional<Reading> reading = this.service.findReadingById(readingId);
        if (reading.isEmpty()){
            return null;
        }

        Optional<Bill> bill = this.billService.findByBillId(billId);
        if(bill.isEmpty()){
            return null;
        }

        Optional<Counter> counter = this.counterService.findbyCounterId(counterId);
        if(counter.isEmpty()){
            return null;
        }

        reading.get().setCounter(counter.get());
        reading.get().setVolumeReading(volume);
        reading.get().setBill(bill.get());
        this.service.save(reading.get());
        return reading.get().toString();
    }
}
