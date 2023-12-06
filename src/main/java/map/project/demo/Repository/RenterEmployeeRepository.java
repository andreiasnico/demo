package map.project.demo.Repository;

import map.project.demo.Model.RenterEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RenterEmployeeRepository extends JpaRepository<RenterEmployee , Long> {

    Optional<RenterEmployee> findRenterEmployeeByRenterEmployeeId(Long renterEmployeeId);
}
