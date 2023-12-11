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

    public Optional<RenterEmployee> findRenterEmployeeById(Long renterEmployeeId){
        return this.renterEmployeeRepository.findRenterEmployeeByRenterEmployeeId(renterEmployeeId);
    }

    public RenterEmployee save(RenterEmployee renterEmployee){
        return this.renterEmployeeRepository.save(renterEmployee);
    }

    public void removeEmployee(RenterEmployee renterEmployee){
        this.renterEmployeeRepository.delete(renterEmployee);
    }
}
