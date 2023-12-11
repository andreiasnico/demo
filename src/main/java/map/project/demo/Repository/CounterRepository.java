package map.project.demo.Repository;

import map.project.demo.Model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends JpaRepository<Counter , Long> {

        Optional<Counter> findByCounterId(Long id);

        Counter deleteCounterByCounterId(Long id);

}
