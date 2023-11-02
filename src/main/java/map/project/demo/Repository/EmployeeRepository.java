package map.project.demo.Repository;

import map.project.demo.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {

        Employee findByEmployeeId(Long id);

        //Todo further implementation of all we need
}
