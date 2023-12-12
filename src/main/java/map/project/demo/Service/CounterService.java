package map.project.demo.Service;

import map.project.demo.Model.Counter;
import map.project.demo.Repository.CounterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Counter> findAll() {
        return counterRepository.findAll();
    }

    public void delete(Counter counter) {
        counterRepository.delete(counter);
    }

    public void deleteCounterById(Long counterId){
        this.counterRepository.deleteCounterByCounterId(counterId);
    }

    public Optional<Counter> findbyCounterId(Long counterId){
        return this.counterRepository.findByCounterId(counterId);
    }

    public void readCounter(Counter counter) {
        counterRepository.findByCounterId(counter.getCounterId());
    }

    public void addCounter(Counter counter) {
        counterRepository.save(counter);
    }


    public List<Counter> findAllCounters() {
        return findAll();
    }
}
