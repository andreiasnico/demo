package map.project.demo.Repository;

import map.project.demo.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {

        Optional<Employee> findByEmployeeId(Long id);

        //Todo further implementation of all we need
}
