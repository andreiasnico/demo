package map.project.demo.Repository;

import map.project.demo.Model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter , Long> {

        Counter findByCounterId(Long id);

    //Todo further implementation of all we need
}
