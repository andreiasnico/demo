package map.project.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import map.project.demo.Model.Renter;
import map.project.demo.Repository.RenterRepository;

import java.util.Optional;

@Service
public class RenterService {
    @Autowired
    private RenterRepository renterRepository;

    public RenterService(RenterRepository renterRepository) {
        this.renterRepository = renterRepository;
    }

    @Transactional
    public Renter save(Renter renter) {
        renterRepository.save(renter);
        return renter;
    }

    public Iterable<Renter> findAll() {
        return renterRepository.findAll();
    }



    public void addRenter(Renter renter) {
        renterRepository.save(renter);
    }

    public void updateRenter(Renter renter) {
        Renter updatedRenter = renterRepository.findById(renter.getRenterId()).get();
        updatedRenter.setRenterId(renter.getRenterId());
        renterRepository.save(updatedRenter);
    }

    public void readRenter(Renter renter) {
        renterRepository.findById(renter.getRenterId());
    }

    public void deleteRenter(Renter deleteRenter) {
        renterRepository.delete(deleteRenter);
    }
    //todo make every method like this try catch exceptions
    public Renter findBYRenterId(Long renterId){
        try {
            Optional<Renter> renter = this.renterRepository.findByRenterId(renterId);
            if (renter.isPresent()) {
                return renter.get();
            }
            else{
                throw new Exception("there is no such employee with this id");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
