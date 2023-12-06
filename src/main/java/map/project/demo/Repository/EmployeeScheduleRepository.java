package map.project.demo.Repository;

import map.project.demo.Model.Employee;
import map.project.demo.Model.EmployeeSchedule;
import map.project.demo.Model.ScheduleKey;
import map.project.demo.Model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule , ScheduleKey> {

    Optional<EmployeeSchedule> findByScheduleKey(ScheduleKey key);

    void deleteByEmployeeAndUnit(Employee employee , Unit unit);

    Optional<EmployeeSchedule> findByEmployeeAndUnit(Employee employee , Unit unit);
}
