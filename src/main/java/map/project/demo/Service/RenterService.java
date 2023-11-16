package map.project.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import map.project.demo.Model.Renter;
import map.project.demo.Repository.RenterRepository;

@Service
public class RenterService {
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

    public void delete(Renter renter) {
        renterRepository.delete(renter);
    }
}
