package map.project.demo.Service;
import org.springframework.stereotype.Service;
import map.project.demo.Model.Counter;
import map.project.demo.Repository.CounterRepository;

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

    public Counter findbyCounterId(Long counterId){
        return this.counterRepository.findByCounterId(counterId);
    }
}
