package map.project.demo.Service;

import map.project.demo.Model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import map.project.demo.Model.Reading;
import map.project.demo.Repository.ReadingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReadingService {
    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private BillService billService;
    public ReadingService(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }


    public Reading save(Reading reading) {
        readingRepository.save(reading);
        Optional<Bill> testBill = this.billService.findByBillId(reading.getBill().getBankStatmentId());
        this.billService.updateBill(testBill.get());
        return reading;
    }

    public Iterable<Reading> findAll() {
        return readingRepository.findAll();
    }

    public void delete(Reading reading) {
        readingRepository.delete(reading);
    }

    public Reading findReadingById(Long readingId){
        return this.findReadingById(readingId);
    }

}
