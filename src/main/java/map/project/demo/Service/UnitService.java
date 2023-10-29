package map.project.demo.Service;

import map.project.demo.Model.Unit;
import map.project.demo.Repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    public Optional<Unit> findByUnitId(Long unitId){
        return unitRepository.findByUnitId(unitId);
    }
}
