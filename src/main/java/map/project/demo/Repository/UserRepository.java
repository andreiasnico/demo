package map.project.demo.Repository;

import map.project.demo.Model.RenterEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RenterEmployee, Long> {
}
