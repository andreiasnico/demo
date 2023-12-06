package map.project.demo.Service;

import map.project.demo.Model.RenterEmployee;
import map.project.demo.Repository.RenterEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer class for the RenterEmployee class
 */
@Service
public class RenterEmployeeService {
    @Autowired
    private RenterEmployeeRepository renterEmployeeRepository;

    /**
     * find all renter employees
     * @return List of renter employees
     */
    public List<RenterEmployee> findAllRenterEmployees(){
        return this.renterEmployeeRepository.findAll();
    }

    public RenterEmployee findRenterEmployeeById(Long renterEmployeeId){
        try{
            Optional<RenterEmployee> employee = this.renterEmployeeRepository.findRenterEmployeeByRenterEmployeeId(renterEmployeeId);
            if(employee.isPresent()){
                return employee.get();
            }
            throw new Exception("there is no employee with this id");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public RenterEmployee save(RenterEmployee renterEmployee){
        return this.renterEmployeeRepository.save(renterEmployee);
    }

    public void removeEmployee(RenterEmployee renterEmployee){
        this.renterEmployeeRepository.delete(renterEmployee);
    }
}
