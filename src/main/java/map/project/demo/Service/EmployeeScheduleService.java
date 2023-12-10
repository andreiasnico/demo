package map.project.demo.Service;

import map.project.demo.Model.Employee;
import map.project.demo.Model.EmployeeSchedule;
import map.project.demo.Model.ScheduleKey;
import map.project.demo.Model.Unit;
import map.project.demo.Repository.EmployeeScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * service layer for the employee schedule class
 */
@Service
public class EmployeeScheduleService {
    @Autowired
    private EmployeeScheduleRepository employeeScheduleRepository;

    public List<EmployeeSchedule> findAllSchedules(){
        return this.employeeScheduleRepository.findAll();
    }
    public EmployeeSchedule addSchedule(EmployeeSchedule schedule){
        return this.employeeScheduleRepository.save(schedule);
    }

    /**
     * method that finds a schedule by the key
     * @param key primary key of the schedule
     * @return Employee schedule
     * if there is no schedule with this id that we throw an exception with a specific message
     */
    public EmployeeSchedule findScheduleById(ScheduleKey key){
        try{
            Optional<EmployeeSchedule> schedule = this.employeeScheduleRepository.findByScheduleKey(key);
            if(schedule.isPresent()){
                return schedule.get();
            }
            throw new Exception("there is no schedule with this id");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public EmployeeSchedule save(EmployeeSchedule schedule){
        return this.employeeScheduleRepository.save(schedule);
    }

    public void deleteSchedule(Employee employee , Unit unit){
        this.employeeScheduleRepository.deleteByEmployeeAndUnit(employee , unit);
    }

    public EmployeeSchedule findSchedule(Employee employee , Unit unit){
        try{
            Optional<EmployeeSchedule> schedule = this.employeeScheduleRepository.findByEmployeeAndUnit(employee , unit);
            if(schedule.isPresent()){
                return schedule.get();
            }
            throw new Exception("there is no schedule for this employee at this unit");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Object findAllEmployeeSchedules() {
        return findAllSchedules();
    }
}
