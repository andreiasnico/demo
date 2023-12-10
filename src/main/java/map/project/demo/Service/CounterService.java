package map.project.demo.Service;

import map.project.demo.Model.Counter;
import map.project.demo.Repository.CounterRepository;
import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private CounterRepository counterRepository;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public Counter save(Counter counter) {
        counterRepository.save(counter);
        return counter;
    }

    public Iterable<Counter> findAll() {
        return counterRepository.findAll();
    }

    public void delete(Counter counter) {
        counterRepository.delete(counter);
    }

    public void deleteCounterById(Long counterId){
        this.counterRepository.deleteCounterByCounterId(counterId);
    }

    public Counter findbyCounterId(Long counterId){
        return this.counterRepository.findByCounterId(counterId);
    }

    public void updateCounter(Counter counter) {
        Counter updatedCounter = counterRepository.findByCounterId(counter.getCounterId());
        updatedCounter.setCounterId(counter.getCounterId());
        counterRepository.save(updatedCounter);
    }

    public void readCounter(Counter counter) {
        counterRepository.findByCounterId(counter.getCounterId());
    }

    public void addCounter(Counter counter) {
        counterRepository.save(counter);
    }


    public Object findAllCounters() {
        return findAll();
    }
}
