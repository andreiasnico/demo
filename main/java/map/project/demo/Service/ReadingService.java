package map.project.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import map.project.demo.Model.Reading;
import map.project.demo.Repository.ReadingRepository;

@Service
public class ReadingService {
    private ReadingRepository readingRepository;

    public ReadingService(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    @Transactional
    public Reading save(Reading reading) {
        readingRepository.save(reading);
        return reading;
    }

    public Iterable<Reading> findAll() {
        return readingRepository.findAll();
    }

    public void delete(Reading reading) {
        readingRepository.delete(reading);
    }
}
